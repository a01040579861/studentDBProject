package javaProject;

import java.util.Objects;

public class Student {
		private String s_num;
		private String s_name;
		private int s_java;
		private int s_android;
		private int s_kotlin;
		private int s_total;
		private double s_avg;
		private String s_grade;
	
		public Student(String s_num, String s_name, int s_java, int s_android, int s_kotlin, int s_total, double s_avg,
				String s_grade) {
			super();
			this.s_num = s_num;
			this.s_name = s_name;
			this.s_java = s_java;
			this.s_android = s_android;
			this.s_kotlin = s_kotlin;
			this.s_total = s_total;
			this.s_avg = s_avg;
			this.s_grade = s_grade;
		}
	
		public Student(String s_num, String s_name, int s_java, int s_android, int s_kotlin) {
			super();
			this.s_num = s_num;
			this.s_name = s_name;
			this.s_java = s_java;
			this.s_android = s_android;
			this.s_kotlin = s_kotlin;
		}
	
		public String getS_num() {
			return s_num;
		}
	
		public void setS_num(String s_num) {
			this.s_num = s_num;
		}
	
		public String getS_name() {
			return s_name;
		}
	
		public void setS_name(String s_name) {
			this.s_name = s_name;
		}
	
		public int getS_java() {
			return s_java;
		}
	
		public void setS_java(int s_java) {
			this.s_java = s_java;
		}
	
		public int getS_android() {
			return s_android;
		}
	
		public void setS_android(int s_android) {
			this.s_android = s_android;
		}
	
		public int getS_kotlin() {
			return s_kotlin;
		}
	
		public void setS_kotlin(int s_kotlin) {
			this.s_kotlin = s_kotlin;
		}
	
		public int getS_total() {
			return s_total;
		}
	
		public void setS_total(int s_total) {
			this.s_total = s_total;
		}
	
		public double getS_avg() {
			return s_avg;
		}
	
		public void setS_avg(double s_avg) {
			this.s_avg = s_avg;
		}
	
		public String getS_grade() {
			return s_grade;
		}
	
		public void setS_grade(String s_grade) {
			this.s_grade = s_grade;
		}
	
		@Override
		public int hashCode() {
			return Objects.hash(s_num);
		}
	
		@Override
		public boolean equals(Object obj) {
			if (obj instanceof Student) {
				Student student = (Student) obj;
				return this.s_num.equals(student.getS_num());
			}
			return true;
		}
	
		@Override
		public String toString() {
			return s_num + "\t" + s_name + "\t" + s_java + "\t" + s_android
					+ "\t" + s_kotlin + "\t" + s_total + "\t" + s_avg + "\t" + s_grade;
		}
}
