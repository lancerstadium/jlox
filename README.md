# Interpreter iterm: jlox

- orgin iterm: https://github.com/GuoYaxiang/craftinginterpreters_zh/tree/main
- Just for Learning.


# 1 Commands
- Compiler Jlox Execution. (v1.0.0 Scanner Test)

```
javac TokenType.java Token.java Scanner.java Lox.java
java -classpath ../../../ com.craftinginterpreters.lox.Lox
```

- Generate "lox/Expr.java" by "tool/GenerateAst.java".

```
javac GenerateAst.java
java -classpath ../../../ com.craftinginterpreters.tool.GenerateAst ../lox/
```

- Checkout AstPrinter Demo.

```
javac Expr.java AstPrinter.java Token.java TokenType.java
java -classpath ../../../ com.craftinginterpreters.lox.AstPrinter
Output: (* (- 123) (group 45.67))
```

- Compiler Jlox Execution. (v2.0.0 Parser Test)

```
javac TokenType.java Token.java Scanner.java AstPrinter.java Expr.java Parser.java Lox.java
java -classpath ../../../ com.craftinginterpreters.lox.Lox
```

- Compiler Jlox Execution. (v2.1.0 Interpreter Test)

```
javac TokenType.java Token.java Scanner.java AstPrinter.java Expr.java Parser.java RuntimeError.java Interpreter.java Lox.java
java -classpath ../../../ com.craftinginterpreters.lox.Lox
```


# 2 Notes

## Expressions Grammar:
1. Literals. Numbers, strings, Booleans, and nil
2. Unary expressions. A prefix ! to perform a logical not, and - to negate a number.
3. Binary expressions. The infix arithmetic (+, -, *, /) and logic operators (==, !=, <, <=, >, >=) we know and love.
4. Parentheses. A pair of ( and ) wrapped around an expression.

- Grammars: 

```
expression     → literal
               | unary
               | binary
               | grouping ;

literal        → NUMBER | STRING | "true" | "false" | "nil" ;
grouping       → "(" expression ")" ;
unary          → ( "-" | "!" ) expression ;
binary         → expression operator expression ;
operator       → "==" | "!=" | "<" | "<=" | ">" | ">="
               | "+"  | "-"  | "*" | "/" ;
```

## Syntax: The same precedence rules as C

- grammar: 

```
expression     → equality ;
equality       → comparison ( ( "!=" | "==" ) comparison )* ;
comparison     → term ( ( ">" | ">=" | "<" | "<=" ) term )* ;
term           → factor ( ( "-" | "+" ) factor )* ;
factor         → unary ( ( "/" | "*" ) unary )* ;
unary          → ( "!" | "-" ) unary
               | primary ;
primary        → NUMBER | STRING | "true" | "false" | "nil"
               | "(" expression ")" ;
```


## Statement

- grammar: 


```
program        → statement* EOF ;

statement      → exprStmt
               | printStmt ;

exprStmt       → expression ";" ;
printStmt      → "print" expression ";" ;

```

# 3 Git

- Git steps: 

```
git add .
git status
git commit -m "v2.1.0 Interpreter"
git push origin main
```
