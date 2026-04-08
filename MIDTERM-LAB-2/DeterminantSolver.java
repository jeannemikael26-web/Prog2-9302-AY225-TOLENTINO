/**
 * =====================================================
 * Student Name    : Jeanne Mikael Tolentino
 * Course          : Math 101 — Linear Algebra
 * Assignment      : Programming Assignment 1 — 3x3 Matrix Determinant Solver
 * School          : University of Perpetual Help System DALTA, Molino Campus
 * Date            : April 8, 2026
 *
 * Description:
 *   Computes the determinant of a hardcoded 3x3 matrix using cofactor
 *   expansion along the first row. Prints each minor, cofactor, and the
 *   final determinant.
 * =====================================================
 */
public class DeterminantSolver {

    // ── SECTION 1: Matrix Declaration ───────────────────────────────────
    // Declare the 3x3 matrix assigned to this student.
    // Values are hardcoded as a 2D integer array in row-major order.
    // Replace the values below with YOUR assigned matrix from the assignment sheet.
    static int[][] matrix = {
        {5, 3, 6},   // Row 1 of assigned matrix
        {2, 4, 3},   // Row 2 of assigned matrix
        {1, 5, 2}    // Row 3 of assigned matrix
    };

    // ── SECTION 2: 2×2 Determinant Helper ───────────────────────────────
    // Computes the determinant of a 2x2 matrix given its four elements.
    // Formula: det = (a * d) - (b * c)
    // This method is called three times during cofactor expansion,
    // once for each of the three 2x2 minors of the first row.
    static int computeMinor(int a, int b, int c, int d) {
        // Apply the 2x2 determinant formula: ad - bc
        return (a * d) - (b * c);
    }

    // ── SECTION 3: Matrix Printer ────────────────────────────────────────
    // Print the 3x3 matrix as plain numbers.
    static void printMatrix(int[][] m) {
        for (int[] row : m) {
            System.out.println(row[0] + " " + row[1] + " " + row[2]);
        }
    }

    // ── SECTION 4: Step-by-Step Determinant Solver ──────────────────────
    // Compute the determinant with cofactor expansion along the first row.
    static void solveDeterminant(int[][] m) {

        // Print the header and the matrix
        System.out.println("3x3 MATRIX DETERMINANT SOLVER");
        System.out.println("Student: Jeanne Mikael Tolentino");
        System.out.println("Assigned Matrix:");
        printMatrix(m);
        System.out.println();
        System.out.println("Cofactor expansion along row 1:");

        // ── Step 1: Compute minor M11 ──
        int minor11 = computeMinor(m[1][1], m[1][2], m[2][1], m[2][2]);
        System.out.printf("Step 1 - Minor M11: det([%d,%d],[%d,%d]) = (%d*%d)-(%d*%d) = %d%n",
            m[1][1], m[1][2], m[2][1], m[2][2],
            m[1][1], m[2][2], m[1][2], m[2][1], minor11);

        // ── Step 2: Compute minor M12 ──
        int minor12 = computeMinor(m[1][0], m[1][2], m[2][0], m[2][2]);
        System.out.printf("Step 2 - Minor M12: det([%d,%d],[%d,%d]) = (%d*%d)-(%d*%d) = %d%n",
            m[1][0], m[1][2], m[2][0], m[2][2],
            m[1][0], m[2][2], m[1][2], m[2][0], minor12);

        // ── Step 3: Compute minor M13 ──
        int minor13 = computeMinor(m[1][0], m[1][1], m[2][0], m[2][1]);
        System.out.printf("Step 3 - Minor M13: det([%d,%d],[%d,%d]) = (%d*%d)-(%d*%d) = %d%n",
            m[1][0], m[1][1], m[2][0], m[2][1],
            m[1][0], m[2][1], m[1][1], m[2][0], minor13);

        // ── Cofactor Terms ──
        int c11 =  m[0][0] * minor11;
        int c12 = -m[0][1] * minor12;
        int c13 =  m[0][2] * minor13;

        System.out.println();
        System.out.printf("Cofactor C11 = (+1) * %d * %d = %d%n", m[0][0], minor11, c11);
        System.out.printf("Cofactor C12 = (-1) * %d * %d = %d%n", m[0][1], minor12, c12);
        System.out.printf("Cofactor C13 = (+1) * %d * %d = %d%n", m[0][2], minor13, c13);

        int det = c11 + c12 + c13;
        System.out.printf("%n det(M) = %d + (%d) + %d%n", c11, c12, c13);
        System.out.printf("DETERMINANT = %d%n", det);

        if (det == 0) {
            System.out.println("The matrix is SINGULAR and has no inverse.");
        }
    }

    // ── SECTION 5: Entry Point ───────────────────────────────────────────
    // The main method is the program's entry point.
    // It calls solveDeterminant() with the student's assigned matrix.
    public static void main(String[] args) {
        solveDeterminant(matrix);
    }

}