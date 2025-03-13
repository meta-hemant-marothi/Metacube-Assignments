# Assignment

Implement the immutable class Poly using an array to represent single variable polynomial. Note that you should be storing only those terms of polynomial that have non zero coefficient. You can assume that the coefficients are integers
It should support methods like - 

1. evaluate(float) - return the value of the polynomial for the given value of the variable

2. degree() - return the degree of the polynomial

3. addPoly(Poly p1, Poly p2) - return the sum of the polynomials p1 and p2

4. multiplyPoly(Poly p1, Poly p2) - return the product of polynomials p1 and p2

You may use private helper methods. addPoly() and multiplyPoly() can make assumptions about size of the resulting polynomial to decide about the size of the array to be created.

### Bonus problem 1

Implement mutable version of Poly where one can add and delete members from the polynomial dynamically, and there is no upper limit on the size of the polynomial.
