# 3×3 Matrix Determinant Solver

**Student Name:** Jeanne Mikael Tolentino  
**Section:** BSIT-GD  
**Course:** Math 101 – Linear Algebra, UPHSD Molino Campus  
**Assignment:** Programming Assignment 1 – 3×3 Matrix Determinant Solver  

## Assigned Matrix

The assigned 3×3 matrix for this assignment is:

```
| 5  3  6 |
| 2  4  3 |
| 1  5  2 |
```

## How to Run the Programs

### Java Program
1. Ensure you have Java installed (JDK 8 or higher).
2. Compile the program:
   ```
   javac DeterminantSolver.java
   ```
3. Run the program:
   ```
   java DeterminantSolver
   ```

### JavaScript Program
1. Ensure you have Node.js installed.
2. Run the program:
   ```
   node determinant_solver.js
   ```

## Sample Output

Both programs produce identical output, showing the step-by-step cofactor expansion along the first row.

### Java Output
```
===================================================
  3x3 MATRIX DETERMINANT SOLVER
  Student: Jeanne Mikael Tolentino
  Assigned Matrix:
===================================================
┌               ┐
│   5   3   6  │
│   2   4   3  │
│   1   5   2  │
└               ┘
===================================================

  Step 1 — Minor M₁₁: det([4,3],[5,2]) = (4×2)-(3×5) = 8-15 = -7
  Step 2 — Minor M₁₂: det([2,3],[1,2]) = (2×2)-(3×1) = 4-3 = 1
  Step 3 — Minor M₁₃: det([2,4],[1,5]) = (2×5)-(4×1) = 10-4 = 6

  Cofactor C₁₁ = (+1) × 5 × -7 = -35
  Cofactor C₁₂ = (-1) × 3 × 1 = -3
  Cofactor C₁₃ = (+1) × 6 × 6 = 36

  det(M) = -35 + (-3) + 36

===================================================
  ✓  DETERMINANT = -2
===================================================
```

### JavaScript Output
```
===================================================
  3x3 MATRIX DETERMINANT SOLVER
  Student: Jeanne Mikael Tolentino
  Assigned Matrix:
===================================================
┌               ┐
│   5   3   6  │
│   2   4   3  │
│   1   5   2  │
└               ┘
===================================================

Expanding along Row 1 (cofactor expansion):

  Step 1 — Minor M₁₁: det([4,3],[5,2]) = (4×2) - (3×5) = -7
  Step 2 — Minor M₁₂: det([2,3],[1,2]) = (2×2) - (3×1) = 1
  Step 3 — Minor M₁₃: det([2,4],[1,5]) = (2×5) - (4×1) = 6

  Cofactor C₁₁ = (+1) × 5 × -7 = -35
  Cofactor C₁₂ = (-1) × 3 × 1 = -3
  Cofactor C₁₃ = (+1) × 6 × 6 = 36

  det(M) = -35 + (-3) + 36
===================================================
  ✓  DETERMINANT = -2
===================================================
```

## Final Determinant Value

The determinant of the assigned matrix is **-2**.

## Repository Structure

This repository contains the following files:
- `DeterminantSolver.java` - Java implementation
- `determinant_solver.js` - JavaScript implementation
- `README.md` - This documentation file