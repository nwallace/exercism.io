module Leap exposing (..)

isDivisible : Int -> Int -> Bool
isDivisible num divisor =
  num % divisor == 0

isLeapYear : Int -> Bool
isLeapYear year =
  (isDivisible year 4) && (not (isDivisible year 100)) || (isDivisible year 400)

