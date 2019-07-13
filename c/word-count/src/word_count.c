#include "word_count.h"

bool
isseperator(const char c) {
  return isspace(c) || c == ',' || c == '.' || c == 0;
}

void
unquote(char *word, size_t word_length) {
  if (word[0] == '\'' && word[word_length - 1] == '\'') {
    word = memmove(word, word + 1, word_length - 2);
    word[word_length - 2] = 0;
  }
}

int
index_of(const char *word, word_count_word_t *words, size_t words_length) {
  for (int i=0; i < words_length; i++) {
    if (strcmp(word, words[i].text) == 0)
      return i;
  }
  return -1;
}

int
word_count(const char *input_text, word_count_word_t *words)
{
  const char *current_char = input_text;
  char current_word[MAX_WORD_LENGTH + 1] = { 0 };
  size_t current_word_idx = 0;
  size_t total_word_count = 0;
  bool done = false;

  memset(words, 0, MAX_WORDS * sizeof(word_count_word_t));

  while (!done) {
    if (isseperator(*current_char)) {
      if (current_word_idx > 0) {
        // record word
        unquote(current_word, current_word_idx);
        int index = index_of(current_word, words, total_word_count - 1);
        if (index < 0) {
          index = total_word_count - 1;
          word_count_word_t wcw;
          strcpy(wcw.text, current_word);
          wcw.count = 0;
          words[index] = wcw;
        } else {
          total_word_count--;
        }
        words[index].count++;

        // re-zero current word
        memset(current_word, 0, current_word_idx * sizeof(char));
        current_word_idx = 0;
      }
      if (*current_char == 0)
        done = true;
    } else {
      if (current_word_idx == 0) {
        total_word_count++;
        if (total_word_count > MAX_WORDS)
          return EXCESSIVE_NUMBER_OF_WORDS;
      } else if (current_word_idx > MAX_WORD_LENGTH) {
        return EXCESSIVE_LENGTH_WORD;
      }
      current_word[current_word_idx++] = tolower(*current_char);
    }
    ++current_char;
  }

  return total_word_count;
}
