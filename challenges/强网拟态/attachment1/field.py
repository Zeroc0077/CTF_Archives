from Crypto.Util.number import inverse

class FieldElement:
    def __init__(self, a, field):
        self.field = field
        self.a = a % self.field.p

    def __add__(self, other):
        return FieldElement(self.a + other.a, self.field)

    def __radd__(self, other):
        return self + other

    def __sub__(self, other):
        return FieldElement(self.a - other.a, self.field)

    def __rsub__(self, other):
        return self - other

    def __neg__(self):
        return FieldElement(-self.a, self.field)

    def __mul__(self, other):
        if isinstance(other, int):
            return FieldElement(self.a*other, self.field)
        return FieldElement(self.a*other.a, self.field)

    def __rmul__(self, other):
        return self*other

    def invert(self):
        if self.a == 0:
            raise ZeroDivisionError
        return FieldElement(inverse(self.a, self.field.p), self.field)

    def __truediv__(self, other):
        return self*invert(other)

    def __pow__(self, exp : int):
        if exp < 0:
            return self.invert()**(-exp)
        return FieldElement(pow(self.a, exp, self.field.p), self.field)

    def __eq__(self, other):
        if isinstance(other, int):
            return self == FieldElement(other, self.field)
        return self.a == other.a and self.field.p == other.field.p

    def __neq__(self, other):
        return not self == other

    def __str__(self):
        return str(self.a)

    def __repr__(self):
        return str(self.a)

    def hex(self):
        return hex(self.a)

    def to_int(self):
        return self.a

    def legendre_symbol(self):
        return self**((self.field.p - 1)//2)

    def sqrt(self):
        if self.legendre_symbol() != 1:
            return 0
        elif self == 0:
            return self
        elif self.field.p % 4 == 3:
            return self**((self.field.p + 1)//4)

        s = self.field.p - 1
        e = 0
        while s % 2 == 0:
            s //= 2
            e += 1

        n = self.field(2)
        while n.legendre_symbol() != -1:
            n = n + 1

        x = a**((s + 1)//2)
        b = a**s
        g = n**s
        r = e

        while True:
            t = b
            m = 0
            for m in xrange(r):
                if t == 1:
                    break
                t = t**2

            if m == 0:
                return x

            gs = g**(2**(r - m - 1))
            g = gs*gs
            x = x*gs
            b = b*g
            r = m

    def __hash__(self):
        return hash(self.a)


class PrimeField:
    def __init__(self, prime):
        self.p = prime

    def __call__(self, a):
        return FieldElement(a, self)