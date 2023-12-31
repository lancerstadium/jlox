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

- Compiler Jlox Execution. (v3.0.0 AST)

```
javac TokenType.java Token.java Scanner.java AstPrinter.java Expr.java Parser.java RuntimeError.java Interpreter.java Stmt.java Environment.java Lox.java
java -classpath ../../../ com.craftinginterpreters.lox.Lox ../test/test1.lox
```

- Generate "lox/Stmt.java" by "tool/GenerateAst.java".

```
javac GenerateAst.java
java -classpath ../../../ com.craftinginterpreters.tool.GenerateAst ../lox/
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


## Statement syntax

- grammar: 


```
program        → statement* EOF ;

statement      → exprStmt
               | printStmt ;

exprStmt       → expression ";" ;
printStmt      → "print" expression ";" ;

```


## Varible syntax

```
program        → declaration* EOF ;

declaration    → varDecl
               | statement ;

statement      → exprStmt
               | printStmt ;

varDecl        → "var" IDENTIFIER ( "=" expression )? ";" ;


```

## Assignment Syntax
```
expression     → assignment ;
assignment     → IDENTIFIER "=" assignment
               | equality ;
```

## Block Syntax

```
statement      → exprStmt
               | printStmt
               | block ;

block          → "{" declaration* "}" ;
```

- try this in exec:
```
var a = "global a";
{var a = "outer a"; {var a = "inner a"; print a;} print a;} print a;


var a = 1; {var a = a + 2; print a; }
```
## Condition Syntax

```
statement      → exprStmt
               | ifStmt
               | printStmt
               | block ;

ifStmt         → "if" "(" expression ")" statement
               ( "else" statement )? ;
```

## Logical Syntax

```
expression     → assignment ;
assignment     → IDENTIFIER "=" assignment
               | logic_or ;
logic_or       → logic_and ( "or" logic_and )* ;
logic_and      → equality ( "and" equality )* ;
```

## Loop Syntax

- while:
```
statement      → exprStmt
               | ifStmt
               | printStmt
               | whileStmt
               | block ;

whileStmt      → "while" "(" expression ")" statement ;
```

- for:
```
statement      → exprStmt
               | forStmt
               | ifStmt
               | printStmt
               | whileStmt
               | block ;

forStmt        → "for" "(" ( varDecl | exprStmt | ";" )
                 expression? ";"
                 expression? ")" statement ;
```


# 3 Git

- Git steps: 

```
git add .
git status
git commit -m "v2.1.0 Interpreter"
git push origin main
```
