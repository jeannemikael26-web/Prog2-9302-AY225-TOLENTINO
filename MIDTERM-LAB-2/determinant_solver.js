/**
 * =====================================================
 * Student Name    : Jeanne Mikael Tolentino
 * Course          : Math 101 — Linear Algebra
 * Assignment      : Programming Assignment 1 — 3x3 Matrix Determinant Solver
 * School          : University of Perpetual Help System DALTA, Molino Campus
 * Date            : April 8, 2026
 * GitHub Repo     : https://github.com/[your-username]/uphsd-cs-tolentino-jeannemikael
 * Runtime         : Node.js (run with: node determinant_solver.js)
 *
 * Description:
 *   This script computes the determinant of a hardcoded 3x3 matrix using
 *   cofactor expansion along the first row. It prints each minor, cofactor,
 *   and the final determinant.
 * =====================================================
 */

// ── SECTION 1: Matrix Declaration ───────────────────────────────────
// The assigned 3x3 matrix is declared as a 2D JavaScript array.
// Replace the values below with YOUR assigned matrix (same values as Java).
// Outer array = rows, inner arrays = individual row values.
const matrix = [
    [5, 3, 6],   // Row 1
    [2, 4, 3],   // Row 2
    [1, 5, 2]    // Row 3
];

// ── SECTION 2: Matrix Printer ────────────────────────────────────────
// Print a 3x3 matrix as plain numbers.
function printMatrix(m) {
    m.forEach(row => {
        console.log(row.join(" "));
    });
}

// ── SECTION 3: 2×2 Determinant Helper ───────────────────────────────
// Return the determinant of a 2x2 matrix.
function computeMinor(a, b, c, d) {
    return (a * d) - (b * c);
}

// ── SECTION 4: Step-by-Step Determinant Solver ──────────────────────
// Main solving function. Accepts the 3x3 matrix and:
//   1. Prints the matrix clearly
//   2. Computes each 2x2 minor (M₁₁, M₁₂, M₁₃)
//   3. Prints the computation of each minor with its arithmetic
//   4. Computes and prints each signed cofactor term
//   5. Sums the cofactors and prints the final determinant
//   6. Checks for a singular matrix (det = 0)
function solveDeterminant(m) {
    // Print problem header
    console.log("3x3 MATRIX DETERMINANT SOLVER");
    console.log("Student: Jeanne Mikael Tolentino");
    console.log("Assigned Matrix:");
    printMatrix(m);
    console.log();
    console.log("Cofactor expansion along row 1:");
    console.log();

    // ── Step 1: Minor M11 ──
    const minor11 = computeMinor(m[1][1], m[1][2], m[2][1], m[2][2]);
    console.log(
        `Step 1 - Minor M11: det([${m[1][1]},${m[1][2]}],[${m[2][1]},${m[2][2]}])` +
        ` = (${m[1][1]}*${m[2][2]}) - (${m[1][2]}*${m[2][1]}) = ${minor11}`
    );

    // ── Step 2: Minor M12 ──
    const minor12 = computeMinor(m[1][0], m[1][2], m[2][0], m[2][2]);
    console.log(
        `Step 2 - Minor M12: det([${m[1][0]},${m[1][2]}],[${m[2][0]},${m[2][2]}])` +
        ` = (${m[1][0]}*${m[2][2]}) - (${m[1][2]}*${m[2][0]}) = ${minor12}`
    );

    // ── Step 3: Minor M13 ──
    const minor13 = computeMinor(m[1][0], m[1][1], m[2][0], m[2][1]);
    console.log(
        `Step 3 - Minor M13: det([${m[1][0]},${m[1][1]}],[${m[2][0]},${m[2][1]}])` +
        ` = (${m[1][0]}*${m[2][1]}) - (${m[1][1]}*${m[2][0]}) = ${minor13}`
    );

    // ── Cofactor Terms ──
    // C₁₁ gets +sign, C₁₂ gets -sign, C₁₃ gets +sign (alternating sign rule)
    const c11 =  m[0][0] * minor11;
    const c12 = -m[0][1] * minor12;
    const c13 =  m[0][2] * minor13;

    console.log();
    console.log(`Cofactor C11 = (+1) * ${m[0][0]} * ${minor11} = ${c11}`);
    console.log(`Cofactor C12 = (-1) * ${m[0][1]} * ${minor12} = ${c12}`);
    console.log(`Cofactor C13 = (+1) * ${m[0][2]} * ${minor13} = ${c13}`);

    // Final determinant
    const det = c11 + c12 + c13;
    console.log();
    console.log(`det(M) = ${c11} + (${c12}) + ${c13}`);
    console.log(`DETERMINANT = ${det}`);

    if (det === 0) {
        console.log("The matrix is SINGULAR and has no inverse.");
    }
}

// ── SECTION 5: Program Entry Point ──────────────────────────────────
// Call the main solver function with the student's assigned matrix.
solveDeterminant(matrix);