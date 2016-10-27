module HelloWorld exposing (..)

helloWorld : Maybe String -> String
helloWorld name =
  case name of
    Nothing ->
      helloWorld(Just "World")
    Just name ->
      "Hello, " ++ name ++ "!"
