[![Build Status](https://travis-ci.org/devTitarenko/Calculator.svg?branch=master)](https://travis-ci.org/devTitarenko/Calculator)

_**COMMAND LINE CALCULATOR**_
-------------------------
_________________________

Launching
-------------------------
Execute in terminal: `gradlew run`.\
`exit` - exit from the program.
 
Args
-------------------------
As an argument, the program expects one parameter - name of Calculator implementation.\
Available two implementations: 
- SimpleCalculator - my own realization *(default)*.
- JavaxCalculator - using javax.script.ScriptEngineManager.

The args you can setup in *build.gradle* file.\

SimpleCalculator
-------------------------
The expression is splitting into nodes. Every node contains two numbers and an operation. For example:
expression "128/4+71-13*2.5+18" will be divided into five nodes:
1) {128, 4, divide}
2) {4, 71, sum}
3) {71, 13, minus}
4) {13, 2.5, multiply}
5) {2.5, 18, sum}

These nodes will be stored into the LinkedList, so each node will have connections to its neighbors.
When the operation is executed - the neighbor nodes will change the value and the initial node will be removed from the list,
until there is only one node left in the list.
- after the first step we will have: {32, 71, sum}, {71, 13, minus}, {13, 2.5, multiply}, {2.5, 18, sum};
- after the second step we will have: {32, 71, sum}, {71, 32.5, minus}, {32.5, 18, sum};
- then: {103, 32.5, minus}, {32.5, 18, sum};
- then: {70.5, 18, sum};
- result: `88.5`.

Operations
-------------------------
Now available only four operations: +, -, * and /. But you could simply add your own by implementing the `Operation` interface.
Operations have priority, so the expression is evaluated in arithmetic order.
The brackets also can be used, but nesting is not allowed. For example:\
`5*(2+(3-9)-3)/2` - will fail\
`5*(2+3)-(9-3)/2` - will be executed correctly

Use `2+(0-1)*3` instead of `2+(-1)*3`.
