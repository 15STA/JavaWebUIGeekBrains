package ru.geekbrains.webui;

    public class Triangle {
        protected int a;
        protected int b;
        protected int c;

        Triangle(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        public double triangleSquare(Triangle triangle) {
            if ((this.a + this.b > this.c) & (this.a + this.c) > this.b & (this.b + this.c) > this.a) {
                double p = ((double) this.a + this.b + this.c) / 2;
                double s = Math.sqrt(p * (p - this.a) * (p - this.b) * (p - this.c));
                return s;
            } else {
                throw new ArithmeticException("Треугольник с заданными сторонами не существует");
            }
        }


    }

