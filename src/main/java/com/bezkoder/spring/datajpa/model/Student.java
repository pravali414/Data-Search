package com.bezkoder.spring.datajpa.model;

import jakarta.persistence.*;
    @Entity
    @Table(name = "student")
    public class Student {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

        @Column(name = "name")
        private String name;

        @Column(name = "marks")
        private int marks;
        @Column(name = "phnumber")
        private int phNumber;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getMarks() {
            return marks;
        }

        public void setMarks(int marks) {
            this.marks = marks;
        }

        public int getPhNumber() {
            return phNumber;
        }

        public void setPhNumber(int phNumber) {
            this.phNumber = phNumber;
        }

        public Student(long id, String name, int marks, int phNumber) {
            this.id = id;
            this.name = name;
            this.marks = marks;
            this.phNumber = phNumber;
        }

        public Student() {
        }

        @Override
        public String toString() {
            return "Student{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", marks=" + marks +
                    ", phnumber=" + phNumber +
                    '}';
        }
    }
