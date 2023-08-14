package com.craftinginterpreters.lox;


abstract class Expr {
  interface Visitor<R> {
    R visitBinaryExpr(Binary expr);
    R visitGroupingExpr(Grouping expr);
    R visitLiteralExpr(Literal expr);
    R visitUnaryExpr(Unary expr);
  }
  // Class: Binary
  static class Binary extends Expr {
    // Constructor: Binary
    Binary(Expr left, Token operator, Expr right) {
      this.left = left;
      this.operator = operator;
      this.right = right;
    }

    @Override
    <R> R accept(Visitor<R> visitor) {
      return visitor.visitBinaryExpr(this);
    }
    // Fields: Binary
    final Expr left;
    final Token operator;
    final Expr right;
  }
  // Class: Grouping
  static class Grouping extends Expr {
    // Constructor: Grouping
    Grouping(Expr expression) {
      this.expression = expression;
    }

    @Override
    <R> R accept(Visitor<R> visitor) {
      return visitor.visitGroupingExpr(this);
    }
    // Fields: Grouping
    final Expr expression;
  }
  // Class: Literal
  static class Literal extends Expr {
    // Constructor: Literal
    Literal(Object value) {
      this.value = value;
    }

    @Override
    <R> R accept(Visitor<R> visitor) {
      return visitor.visitLiteralExpr(this);
    }
    // Fields: Literal
    final Object value;
  }
  // Class: Unary
  static class Unary extends Expr {
    // Constructor: Unary
    Unary(Token operator, Expr right) {
      this.operator = operator;
      this.right = right;
    }

    @Override
    <R> R accept(Visitor<R> visitor) {
      return visitor.visitUnaryExpr(this);
    }
    // Fields: Unary
    final Token operator;
    final Expr right;
  }

  abstract <R> R accept(Visitor<R> visitor);
}
