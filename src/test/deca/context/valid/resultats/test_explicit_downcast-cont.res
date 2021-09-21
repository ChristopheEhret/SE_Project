`> [14, 0] Program
   +> [21, 0] ListDeclClass [List with 3 elements]
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
   |  []> [14, 0] DeclClass
   |  ||  +> [14, 6] Identifier (A)
   |  ||  |  definition: type defined at [14, 0], type=A
   |  ||  +> Identifier (Object)
   |  ||  |  definition: type defined at [0, 0], type=Object
   |  ||  +> [15, 5] ListDeclField [List with 1 elements]
   |  ||  |  []> [15, 5] DeclField
   |  ||  |      +> [15, 1] Identifier (int)
   |  ||  |      |  definition: type defined at [0, 0], type=int
   |  ||  |      +> [15, 5] Identifier (x)
   |  ||  |      |  definition: field defined at [15, 5], type=int
   |  ||  |      `> [15, 7] Initialization
   |  ||  |         `> [15, 9] Int (5)
   |  ||  |            type: int
   |  ||  `> ListDeclMethod [List with 1 elements]
   |  ||     []> [16, 1] DeclMethod
   |  ||         +> [16, 1] Identifier (int)
   |  ||         |  definition: type defined at [0, 0], type=int
   |  ||         +> [16, 5] Identifier (getX)
   |  ||         |  definition: method defined at [16, 1], type=int
   |  ||         +> ListDeclParam [List with 0 elements]
   |  ||         `> [16, 11] MethodBody
   |  ||            +> ListDeclVar [List with 0 elements]
   |  ||            `> ListInst [List with 1 elements]
   |  ||               []> [17, 2] Return
   |  ||                   `> [17, 9] Identifier (x)
   |  ||                      definition: field defined at [15, 5], type=int
   |  []> [21, 0] DeclClass
   |      +> [21, 6] Identifier (B)
   |      |  definition: type defined at [21, 0], type=B
   |      +> [21, 8] Identifier (A)
   |      |  definition: type defined at [14, 0], type=A
   |      +> [22, 7] ListDeclField [List with 1 elements]
   |      |  []> [22, 7] DeclField
   |      |      +> [22, 1] Identifier (float)
   |      |      |  definition: type defined at [0, 0], type=float
   |      |      +> [22, 7] Identifier (f)
   |      |      |  definition: field defined at [22, 7], type=float
   |      |      `> [22, 9] Initialization
   |      |         `> [22, 11] Float (2.3)
   |      |            type: float
   |      `> ListDeclMethod [List with 1 elements]
   |         []> [23, 1] DeclMethod
   |             +> [23, 1] Identifier (float)
   |             |  definition: type defined at [0, 0], type=float
   |             +> [23, 7] Identifier (getF)
   |             |  definition: method defined at [23, 1], type=float
   |             +> ListDeclParam [List with 0 elements]
   |             `> [23, 13] MethodBody
   |                +> ListDeclVar [List with 0 elements]
   |                `> ListInst [List with 1 elements]
   |                   []> [24, 2] Return
   |                       `> [24, 9] Identifier (f)
   |                          definition: field defined at [22, 7], type=float
   `> [28, 0] Main
      +> [31, 3] ListDeclVar [List with 2 elements]
      |  []> [30, 3] DeclVar
      |  ||  +> [30, 1] Identifier (A)
      |  ||  |  definition: type defined at [14, 0], type=A
      |  ||  +> [30, 3] Identifier (a)
      |  ||  |  definition: variable defined at [30, 3], type=A
      |  ||  `> [30, 5] Initialization
      |  ||     `> [30, 7] New
      |  ||        type: B
      |  ||        `> [30, 11] Identifier (B)
      |  ||           definition: type defined at [21, 0], type=B
      |  []> [31, 3] DeclVar
      |      +> [31, 1] Identifier (B)
      |      |  definition: type defined at [21, 0], type=B
      |      +> [31, 3] Identifier (b)
      |      |  definition: variable defined at [31, 3], type=B
      |      `> [31, 5] Initialization
      |         `> [31, 7] Cast
      |            type: B
      |            +> [31, 8] Identifier (B)
      |            |  definition: type defined at [21, 0], type=B
      |            `> [31, 12] Identifier (a)
      |               definition: variable defined at [30, 3], type=A
      `> ListInst [List with 0 elements]
