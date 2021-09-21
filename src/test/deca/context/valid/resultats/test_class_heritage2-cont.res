`> [15, 0] Program
   +> [29, 0] ListDeclClass [List with 4 elements]
   |  []> [0, 0] DeclClass
   |  ||  +> [0, 0] Identifier (Object)
   |  ||  |  definition: type defined at [0, 0], type=Object
   |  ||  +> [0, 0] Identifier (0)
   |  ||  |  definition: type defined at [0, 0], type=0
   |  ||  +> ListDeclField [List with 0 elements]
   |  ||  `> [0, 0] ListDeclMethod [List with 1 elements]
   |  ||     []> [0, 0] DeclMethod
   |  ||         +> [0, 0] Identifier (boolean)
   |  ||         |  definition: type defined at [0, 0], type=boolean
   |  ||         +> [0, 0] Identifier (equals)
   |  ||         |  definition: method defined at [0, 0], type=boolean
   |  ||         +> [0, 0] ListDeclParam [List with 1 elements]
   |  ||         |  []> [0, 0] DeclParam
   |  ||         |      +> [0, 0] Identifier (Object)
   |  ||         |      |  definition: type defined at [0, 0], type=Object
   |  ||         |      `> [0, 0] Identifier (o)
   |  ||         |         definition: parameter defined at [0, 0], type=Object
   |  ||         `> [0, 0] MethodBody
   |  ||            +> ListDeclVar [List with 0 elements]
   |  ||            `> [0, 0] ListInst [List with 1 elements]
   |  ||               []> [0, 0] Return
   |  ||                   `> [0, 0] Equals
   |  ||                      type: boolean
   |  ||                      +> [0, 0] This
   |  ||                      |  type: Object
   |  ||                      `> [0, 0] Identifier (o)
   |  ||                         definition: parameter defined at [0, 0], type=Object
   |  []> [15, 0] DeclClass
   |  ||  +> [15, 6] Identifier (A)
   |  ||  |  definition: type defined at [15, 0], type=A
   |  ||  +> Identifier (Object)
   |  ||  |  definition: type defined at [0, 0], type=Object
   |  ||  +> [16, 5] ListDeclField [List with 1 elements]
   |  ||  |  []> [16, 5] DeclField
   |  ||  |      +> [16, 1] Identifier (int)
   |  ||  |      |  definition: type defined at [0, 0], type=int
   |  ||  |      +> [16, 5] Identifier (x)
   |  ||  |      |  definition: field defined at [16, 5], type=int
   |  ||  |      `> [16, 7] Initialization
   |  ||  |         `> [16, 9] Int (5)
   |  ||  |            type: int
   |  ||  `> ListDeclMethod [List with 1 elements]
   |  ||     []> [17, 1] DeclMethod
   |  ||         +> [17, 1] Identifier (int)
   |  ||         |  definition: type defined at [0, 0], type=int
   |  ||         +> [17, 5] Identifier (getX)
   |  ||         |  definition: method defined at [17, 1], type=int
   |  ||         +> ListDeclParam [List with 0 elements]
   |  ||         `> [17, 11] MethodBody
   |  ||            +> ListDeclVar [List with 0 elements]
   |  ||            `> ListInst [List with 1 elements]
   |  ||               []> [18, 2] Return
   |  ||                   `> [18, 9] Identifier (x)
   |  ||                      definition: field defined at [16, 5], type=int
   |  []> [22, 0] DeclClass
   |  ||  +> [22, 6] Identifier (B)
   |  ||  |  definition: type defined at [22, 0], type=B
   |  ||  +> [22, 8] Identifier (A)
   |  ||  |  definition: type defined at [15, 0], type=A
   |  ||  +> [23, 9] ListDeclField [List with 1 elements]
   |  ||  |  []> [23, 9] DeclField
   |  ||  |      +> [23, 1] Identifier (boolean)
   |  ||  |      |  definition: type defined at [0, 0], type=boolean
   |  ||  |      +> [23, 9] Identifier (b)
   |  ||  |      |  definition: field defined at [23, 9], type=boolean
   |  ||  |      `> [23, 11] Initialization
   |  ||  |         `> [23, 13] BooleanLiteral (true)
   |  ||  |            type: boolean
   |  ||  `> ListDeclMethod [List with 1 elements]
   |  ||     []> [24, 1] DeclMethod
   |  ||         +> [24, 1] Identifier (boolean)
   |  ||         |  definition: type defined at [0, 0], type=boolean
   |  ||         +> [24, 9] Identifier (getB)
   |  ||         |  definition: method defined at [24, 1], type=boolean
   |  ||         +> ListDeclParam [List with 0 elements]
   |  ||         `> [24, 15] MethodBody
   |  ||            +> ListDeclVar [List with 0 elements]
   |  ||            `> ListInst [List with 1 elements]
   |  ||               []> [25, 2] Return
   |  ||                   `> [25, 9] Identifier (b)
   |  ||                      definition: field defined at [23, 9], type=boolean
   |  []> [29, 0] DeclClass
   |      +> [29, 6] Identifier (C)
   |      |  definition: type defined at [29, 0], type=C
   |      +> [29, 8] Identifier (B)
   |      |  definition: type defined at [22, 0], type=B
   |      +> [30, 7] ListDeclField [List with 1 elements]
   |      |  []> [30, 7] DeclField
   |      |      +> [30, 1] Identifier (float)
   |      |      |  definition: type defined at [0, 0], type=float
   |      |      +> [30, 7] Identifier (f)
   |      |      |  definition: field defined at [30, 7], type=float
   |      |      `> [30, 9] Initialization
   |      |         `> [30, 11] Float (2.3)
   |      |            type: float
   |      `> ListDeclMethod [List with 1 elements]
   |         []> [31, 1] DeclMethod
   |             +> [31, 1] Identifier (float)
   |             |  definition: type defined at [0, 0], type=float
   |             +> [31, 7] Identifier (getF)
   |             |  definition: method defined at [31, 1], type=float
   |             +> ListDeclParam [List with 0 elements]
   |             `> [31, 13] MethodBody
   |                +> ListDeclVar [List with 0 elements]
   |                `> ListInst [List with 1 elements]
   |                   []> [32, 2] Return
   |                       `> [32, 9] Identifier (f)
   |                          definition: field defined at [30, 7], type=float
   `> [36, 0] Main
      +> [38, 3] ListDeclVar [List with 1 elements]
      |  []> [38, 3] DeclVar
      |      +> [38, 1] Identifier (C)
      |      |  definition: type defined at [29, 0], type=C
      |      +> [38, 3] Identifier (c)
      |      |  definition: variable defined at [38, 3], type=C
      |      `> [38, 5] Initialization
      |         `> [38, 7] New
      |            type: C
      |            `> [38, 11] Identifier (C)
      |               definition: type defined at [29, 0], type=C
      `> ListInst [List with 1 elements]
         []> [39, 1] IfThenElse
             +> [39, 4] MethodCall
             |  type: boolean
             |  +> [39, 4] Identifier (c)
             |  |  definition: variable defined at [38, 3], type=C
             |  +> [39, 6] Identifier (getB)
             |  |  definition: method defined at [24, 1], type=boolean
             |  `> ListExpr [List with 0 elements]
             +> ListInst [List with 1 elements]
             |  []> [40, 2] Println
             |      `> [40, 10] ListExpr [List with 2 elements]
             |         []> [40, 10] MethodCall
             |         ||  type: int
             |         ||  +> [40, 10] Identifier (c)
             |         ||  |  definition: variable defined at [38, 3], type=C
             |         ||  +> [40, 12] Identifier (getX)
             |         ||  |  definition: method defined at [17, 1], type=int
             |         ||  `> ListExpr [List with 0 elements]
             |         []> [40, 19] MethodCall
             |             type: float
             |             +> [40, 19] Identifier (c)
             |             |  definition: variable defined at [38, 3], type=C
             |             +> [40, 21] Identifier (getF)
             |             |  definition: method defined at [31, 1], type=float
             |             `> ListExpr [List with 0 elements]
             `> ListInst [List with 0 elements]
