from field import PrimeField

def conditional_swap(bit, R0, R1):
    if bit == 1:
        R0, R1 = R1, R0
    return R0, R1

class Curve:
    def __init__(self, p, a, b, order, x, y):
        self.field = PrimeField(p)
        self.a = self.field(a)
        self.b = self.field(b)
        self.order = order
        self.base = self.field(x), self.field(y)
        self.infty = self.field(1), self.field(1), self.field(0)

    def to_affine(self, P):
        X, Y, Z = P
        if Z == 0:
            return self.infty
        tcub = Z ** -1
        tsqr = tcub ** 2
        tcub = tsqr * tcub
        return X * tsqr, Y * tcub

    def add_jac(self, P1, P2):
        X1, Y1, Z1 = P1
        X2, Y2, Z2 = P2

        if Z1 == 0:
            return P2
        if Z2 == 0:
            return P1

        Z1Z1 = Z1 ** 2
        Z2Z2 = Z2 ** 2
        U1 = X1 * Z2Z2
        U2 = X2 * Z1Z1
        t0 = Z2 * Z2Z2
        S1 = Y1 * t0
        t1 = Z1 * Z1Z1
        S2 = Y2 * t1
        H = U2 - U1
        t2 = 2 * H
        I = t2 ** 2
        J = H * I
        t3 = S2 - S1
        if H == 0:
            if t3 == 0:
                return self.dbl_jac(P1)
            else:
                return self.infty
        r = 2 * t3
        V = U1 * I
        t4 = r ** 2
        t5 = 2 * V
        t6 = t4 - J
        X3 = t6 - t5
        t7 = V - X3
        t8 = S1 * J
        t9 = 2 * t8
        t10 = r * t7
        Y3 = t10 - t9
        t11 = Z1 + Z2
        t12 = t11 ** 2
        t13 = t12 - Z1Z1
        t14 = t13 - Z2Z2
        Z3 = t14 * H

        return X3, Y3, Z3

    def dbl_jac(self, P1):
        X1, Y1, Z1 = P1
        if Z1 == 0:
            return P1

        XX = X1 ** 2
        YY = Y1 ** 2
        YYYY = YY ** 2
        ZZ = Z1 ** 2
        t0 = X1 + YY
        t1 = t0 ** 2
        t2 = t1 - XX
        t3 = t2 - YYYY
        S = 2 * t3
        t4 = ZZ ** 2
        t5 = self.a * t4
        t6 = 3 * XX
        M = t6 + t5
        t7 = M ** 2
        t8 = 2 * S
        T = t7 - t8
        X3 = T
        t9 = S - T
        t10 = 8 * YYYY
        t11 = M * t9
        Y3 = t11 - t10
        t12 = Y1 + Z1
        t13 = t12 ** 2
        t14 = t13 - YY
        Z3 = t14 - ZZ
        return X3, Y3, Z3

    def mult(self, k, P, skip=-1):
        if k == 0:
            return self.infty
        if k == 1:
            return P

        n = k.bit_length()
        R0 = P[0], P[1], self.field(1)
        R1 = self.dbl_jac(R0)

        condition = 0
        for i in range(n - 2, -1, -1):
            ki = (k >> i) & 1
            condition ^= ki
            R0, R1 = conditional_swap(condition, R0, R1)
            R1 = self.add_jac(R0, R1)
            R0 = self.dbl_jac(R0)
            if i != skip: condition = ki

        R0, R1 = conditional_swap(k & 1, R0, R1)

        return self.to_affine(R0)
