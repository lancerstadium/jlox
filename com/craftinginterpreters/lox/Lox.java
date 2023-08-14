package com.craftinginterpreters.lox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

// Lox: Application shell for interpreter.
public class Lox {
	// Interpreter define for lox.interpreter.
	private static final Interpreter interpreter = new Interpreter();

	// Don't execute error code and exit with a number.
	static boolean hadError = false;
	static boolean hadRuntimeError = false;

	// Main Function: Entry of the interpreter execution.
	public static void main(String[] args) throws IOException {
		if (args.length > 1) {	
			System.out.println("Usage: jlox [script]");
			System.exit(64);	
		} else if (args.length == 1) {
			// Console enter jlox: read file
			runFile(args[0]);
		} else {
			runPrompt();
		}
	
	}
 
  	// runFile: Read file from path and execute
  	private static void runFile(String path) throws IOException {
  		byte[] bytes = Files.readAllBytes(Paths.get(path));
  		run(new String(bytes, Charset.defaultCharset()));
  		// Indicate an error in the exit code.
  		if (hadError) System.exit(65);
		if (hadRuntimeError) System.exit(70);
  	}
  	
  	
  	// runPrompt: Conversation with interpreter without arguments
  	private static void runPrompt() throws IOException {
  		InputStreamReader input = new InputStreamReader(System.in);
  		BufferedReader reader = new BufferedReader(input);
  		
		System.out.println("----------------------------Jlox-------------------------");
		System.out.println("[Jlox]> Welcome to Jlox v2.1.0 !");
		System.out.println("[Jlox]> Time: 2023/8/14 Author: Lancer");
		System.out.println("[Jlox]> Start with your commod: ");
  		// main loop
  		for (;;) { 
  			System.out.print("> ");
  			String line = reader.readLine();
  			if (line == null) break;
  			run(line);
  			// Reset hadError and hold on session.
  			hadError = false;
  		}
  	}
  	
  	
  	// run: Kernel function of running execution
  	private static void run(String source) {
  		Scanner scanner = new Scanner(source);
  		List<Token> tokens = scanner.scanTokens();
  		
  		/* 
		// v1.0.0 Scanner Test
		// TODO: For now, just print the tokens.
  		for (Token token : tokens) {
  			System.out.println(token);
  		}
		*/
		
		Parser parser = new Parser(tokens);
		Expr expression = parser.parse();
		// Stop if there was a syntax error.
		if (hadError) return;
		// v2.0.0 Parser Test
		// TODO: For now, print the syntax tree.
		// System.out.println(new AstPrinter().print(expression));
		// v2.1.0 Interpreter Test
		// TODO: For now, execute the syntax tree.
		interpreter.interpret(expression);

  	
  	}	
  	
  	
  	// error: Print error infomation
  	static void error(int line, String message) {
  		report(line, "", message);
  		
  	}
    
   	// report: Print error line and message
    	private static void report(int line, String where, String message) {
		System.err.println("[line " + line + "] Error" + where + ": " + message);
		hadError = true;
 	}
	
	// error: Report user's error information.
	static void error(Token token, String message) {
		if (token.type == TokenType.EOF) {
			report(token.line, " at end", message);
		} else {
			report(token.line, " at '" + token.lexeme + "'", message);
		}
	}

	// runtimeError: Report runtime error information.
	static void runtimeError(RuntimeError error) {
		System.err.println(error.getMessage() + "\n[line " + error.token.line + "]");
		hadRuntimeError = true;
	}
}











