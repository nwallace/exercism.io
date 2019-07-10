#include "acronym.h"

struct StringChar {
  char character;
  struct StringChar *next;
};
typedef struct StringChar StringChar;

StringChar *
string_push(StringChar *previous, char character)
{
  StringChar *new_char = calloc(1, sizeof(StringChar));
  new_char->character = character;
  if (previous != NULL) {
    previous->next = new_char;
  }
  return new_char;
}

char *
string_char_to_string(StringChar *string_char) {
  size_t length = 0;
  StringChar *cursor = string_char;
  while (cursor != NULL) {
    length++;
    cursor = cursor->next;
  }
  char *string = calloc(length + 1, sizeof(char));
  cursor = string_char;
  for (size_t i=0; cursor != NULL; i++) {
    string[i] = cursor->character;
    cursor = cursor->next;
  }
  return string;
}

void
string_destroy(StringChar *string) {
  if (string->next != NULL) {
    string_destroy(string->next);
  }
  free(string);
}

char *
abbreviate(const char *phrase)
{
  if (phrase == NULL || phrase[0] == 0)
    return NULL;

  StringChar *abbreviation = string_push(NULL, toupper(phrase[0])),
             *last_char = abbreviation;

  for (size_t i=1; phrase[i+1] != 0; i++) {
    if (!isalpha(phrase[i]) && isalpha(phrase[i+1])) {
      last_char = string_push(last_char, toupper(phrase[i+1]));
      i++;
    }
  }

  char *result = string_char_to_string(abbreviation);
  string_destroy(abbreviation);
  return result;
}
