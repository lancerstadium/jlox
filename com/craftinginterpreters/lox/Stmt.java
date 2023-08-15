package com.craftinginterpreters.lox;

import java.util.List;

abstract class Stmt {
  interface Visitor<R> {
    R visitBlockStmt(Block stmt);
    R visitExpressionStmt(Expression stmt);
    R visitIfStmt(If stmt);
    R visitPrintStmt(Print stmt);
    R visitVarStmt(Var stmt);
    R visitWhileStmt(While stmt);
  }
  // Class: Block
  static class Block extends Stmt {
    // Constructor: Block
    Block(List<Stmt> statements) {
      this.statements = statements;
    }

    @Override
    <R> R accept(Visitor<R> visitor) {
      return visitor.visitBlockStmt(this);
    }
    // Fields: Block
    final List<Stmt> statements;
  }
  // Class: Expression
  static class Expression extends Stmt {
    // Constructor: Expression
    Expression(Expr expression) {
      this.expression = expression;
    }

    @Override
    <R> R accept(Visitor<R> visitor) {
      return visitor.visitExpressionStmt(this);
    }
    // Fields: Expression
    final Expr expression;
  }
  // Class: If
  static class If extends Stmt {
    // Constructor: If
    If(Expr condition, Stmt thenBranch, Stmt elseBranch) {
      this.condition = condition;
      this.thenBranch = thenBranch;
      this.elseBranch = elseBranch;
    }

    @Override
    <R> R accept(Visitor<R> visitor) {
      return visitor.visitIfStmt(this);
    }
    // Fields: If
    final Expr condition;
    final Stmt thenBranch;
    final Stmt elseBranch;
  }
  // Class: Print
  static class Print extends Stmt {
    // Constructor: Print
    Print(Expr expression) {
      this.expression = expression;
    }

    @Override
    <R> R accept(Visitor<R> visitor) {
      return visitor.visitPrintStmt(this);
    }
    // Fields: Print
    final Expr expression;
  }
  // Class: Var
  static class Var extends Stmt {
    // Constructor: Var
    Var(Token name, Expr initializer) {
      this.name = name;
      this.initializer = initializer;
    }

    @Override
    <R> R accept(Visitor<R> visitor) {
      return visitor.visitVarStmt(this);
    }
    // Fields: Var
    final Token name;
    final Expr initializer;
  }
  // Class: While
  static class While extends Stmt {
    // Constructor: While
    While(Expr condition, Stmt body) {
      this.condition = condition;
      this.body = body;
    }

    @Override
    <R> R accept(Visitor<R> visitor) {
      return visitor.visitWhileStmt(this);
    }
    // Fields: While
    final Expr condition;
    final Stmt body;
  }

  abstract <R> R accept(Visitor<R> visitor);
}
