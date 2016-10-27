module Bob exposing (..)

import Regex exposing (contains, regex)

type Classification = Question | Shout | Silence | Statement

classify : String -> Classification
classify saying =
  if contains(regex "^\\s*$") saying then
    Silence
  else if contains(regex "^[^a-z]+$") saying && contains(regex "[A-Z]") saying then
    Shout
  else if contains(regex "\\?$") saying then
    Question
  else
    Statement

hey : String -> String
hey saidWhat =
  case classify saidWhat of
    Question  -> "Sure."
    Shout     -> "Whoa, chill out!"
    Silence   -> "Fine. Be that way!"
    Statement -> "Whatever."
