# Cafe Lambda

A just-for-fun implementation of a Lambda Calculus interpreter, in Java.


## Setup

Known good setup:
  * Java JDK: 11.0.1 (Oracle Corporation 11.0.1+13-LTS)
  * Gradle: 4.10.2 (can be installed via [Homebrew](https://brew.sh/))
  * IntelliJ: 2018.3


## Test

    $ gradle test


## Run

    $ gradle run


## Resources

  * [Lambda Calculus](https://en.wikipedia.org/wiki/Lambda_calculus)
    * [Formal Language Definition](https://en.wikipedia.org/wiki/Lambda_calculus_definition)
    * [Church Numerals](https://en.wikipedia.org/wiki/Church_encoding#Church_numerals)
      * [Peano Axioms](https://en.wikipedia.org/wiki/Peano_axioms)
      * [@every_peano](https://twitter.com/every_peano)
  * [Abstract Syntax Tree](https://en.wikipedia.org/wiki/Abstract_syntax_tree)


## Scratch

```
((\age. (isMillenial age)) forty)
=>
(isMillenial forty)

(λf. (λx. x))
(λf. (λx. (f x)))
(λf. (λx. (f (f x))))

(λisMillenial. (λage. age))
```