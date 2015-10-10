package com.nb.core;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;

import com.nb.data.DataType;
import com.nb.data.Variable;

public class Compiler {

	public static String textFile;
	public Scanner fileScanner;
	public String line;
	String userInput = "";
	String writeLine = "";

	ArrayList<Variable> vars = new ArrayList<Variable>();

	public static void main(String[] args) {
		textFile = args[0];
		new Compiler();
	}

	public Compiler() {
		load();
	}

	public void load() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(textFile));
			StringBuilder sb = new StringBuilder();
			line = br.readLine();

			while (line != null) {
				sb.append(line + "\n");
				line = br.readLine();
			}

			line = sb.toString();
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		parse(line);
	}

	public void parse(String data) {

		String[] lines = data.split("\n");

		for (int i = 0; i < lines.length; i++) {

			String[] arg = lines[i].split(": ");

			if (lines[i].contains("print")) {
				if (arg[1].equals("input")) {
					System.out.println(userInput);
				}
				for (Variable v : vars) {
					if (arg[1].equals(v.getName())) {
						System.out.println(v.getValue());
					} else {
						System.out.println(arg[1]);
					}
				}
			}

			if (lines[i].contains("add")) {
				for (Variable v : vars) {
					if (arg[1].equals(v.getName())
							&& v.getDataType() == DataType.NUM) {
						v.setValue(Integer.parseInt((String) v
								.getValue()) + Integer.parseInt(arg[2]));
						break;
					}
					if (arg[2].equals(v.getName())
							&& v.getDataType() == DataType.NUM) {
						v.setValue(Integer.parseInt(arg[1])
								+ Integer.parseInt((String) v.getValue()));
						break;
					}
				}
			}

			if (lines[i].contains("sub")) {
				for (Variable v : vars) {
					if (arg[1].equals(v.getName())
							&& v.getDataType() == DataType.NUM) {
						v.setValue(Integer.parseInt((String) v
								.getValue()) - Integer.parseInt(arg[2]));
						break;
					}
					if (arg[2].equals(v.getName())
							&& v.getDataType() == DataType.NUM) {
						v.setValue(Integer.parseInt(arg[1])
								- Integer.parseInt((String) v.getValue()));
						break;
					}
				}
			}

			if (lines[i].contains("mul")) {
				for (Variable v : vars) {
					if (arg[1].equals(v.getName())
							&& v.getDataType() == DataType.NUM) {
						v.setValue(Integer.parseInt((String) v
								.getValue()) * Integer.parseInt(arg[2]));
						break;
					}
					if (arg[2].equals(v.getName())
							&& v.getDataType() == DataType.NUM) {
						v.setValue(Integer.parseInt(arg[1])
								* Integer.parseInt((String) v.getValue()));
						break;
					}
				}
			}

			if (lines[i].contains("div")) {
				for (Variable v : vars) {
					if (arg[1].equals(v.getName())
							&& v.getDataType() == DataType.NUM) {
						v.setValue(Integer.parseInt((String) v
								.getValue()) / Integer.parseInt(arg[2]));
						break;
					}
					if (arg[2].equals(v.getName())
							&& v.getDataType() == DataType.NUM) {
						v.setValue(Integer.parseInt(arg[1])
								/ Integer.parseInt((String) v.getValue()));
						break;
					}
				}
			}

			if (lines[i].contains("exp")) {
				for (Variable v: vars) {
					if (arg[1].equals(v.getName())
							&& v.getDataType() == DataType.NUM) {
						v.setValue(Integer.parseInt((String) v
								.getValue()) ^ Integer.parseInt(arg[2]));
						break;
					}
					if (arg[2].equals(v.getName())
							&& v.getDataType() == DataType.NUM) {
						v.setValue(Integer.parseInt(arg[1])
								^ Integer.parseInt((String) v.getValue()));
						break;
					}
				}
			}

			if (lines[i].contains("input")) {
				userInput = new Scanner(System.in).nextLine();
				if (arg[1] != null) {
					vars.add(new Variable(arg[1], userInput, DataType.STRING));
				}
			}

			if (lines[i].equals("exit")) {
				System.exit(0);
			}

			if (lines[i].contains("write")) {
				try {
					PrintWriter pr = new PrintWriter(arg[1]);
					if (arg[2].equals("input")) {
						pr.write(userInput);
					} else {
						pr.write(arg[2]);
					}
					pr.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}

			if (lines[i].contains("pause") || lines[i].contains("delay")) {
				try {
					Thread.sleep(Integer.parseInt(arg[1]));
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			if (lines[i].contains("sin")) {
				for (Variable v: vars) {
					if (arg[1].equals(v.getName())
							&& v.getDataType() == DataType.NUM) {
						v.setValue(Math.sin(Integer.parseInt((String) v
								.getValue())));
						break;
					}
				}
			}

			if (lines[i].contains("cos")) {
				for (Variable v: vars) {
					if (arg[1].equals(v.getName())
							&& v.getDataType() == DataType.NUM) {
						v.setValue(Math.cos(Integer.parseInt((String) v
								.getValue())));
						break;
					}
				}
			}

			if (lines[i].contains("rand")) {
				System.out.println(Math.random());
			}

			if (lines[i].contains("ceil")) {
				for (Variable v: vars) {
					if (arg[1].equals(v.getName())
							&& v.getDataType() == DataType.NUM) {
						v.setValue(Math.ceil(Integer.parseInt((String) v
								.getValue())));
						break;
					}
				}
			}

			if (lines[i].contains("floor")) {
				for (Variable v: vars) {
					if (arg[1].equals(v.getName())
							&& v.getDataType() == DataType.NUM) {
						v.setValue(Math.floor(Integer.parseInt((String) v
								.getValue())));
						break;
					}
				}
			}

			if (lines[i].contains("sqr")) {
				for (Variable v: vars) {
					if (arg[1].equals(v.getName())
							&& v.getDataType() == DataType.NUM) {
						v.setValue(Math.sqrt(Integer.parseInt((String) v
								.getValue())));
						break;
					}
				}
			}

			if (lines[i].contains("tan")) {
				for (Variable v: vars) {
					if (arg[1].equals(v.getName())
							&& v.getDataType() == DataType.NUM) {
						v.setValue(Math.tan(Integer.parseInt((String) v
								.getValue())));
						break;
					}
				}
			}

			if (lines[i].contains("frame")) {
				int width = Integer.parseInt(arg[2]);
				int height = Integer.parseInt(arg[3]);
				JFrame frame = new JFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setTitle(arg[1]);
				frame.setSize(width, height);
				frame.setResizable(false);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
			}

			if (lines[i].contains("string")) {
				vars.add(new Variable(arg[1], arg[2], DataType.STRING));
			}
			if (lines[i].contains("double")) {
				vars.add(new Variable(arg[1], Double.parseDouble(arg[2]),
						DataType.DOUBLE));
			}
			if (lines[i].contains("long")) {
				vars.add(new Variable(arg[1], Long.parseLong(arg[2]),
						DataType.LONG));
			}
			if (lines[i].contains("num")) {
				vars.add(new Variable(arg[1], arg[2], DataType.NUM));
			}
		}
	}
}
