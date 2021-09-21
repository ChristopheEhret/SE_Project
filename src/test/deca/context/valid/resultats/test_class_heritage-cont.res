`> [14, 0] Program
   +> [24, 0] ListDeclClass [List with 3 elements]
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
   |  ||  +> [16, 7] ListDeclField [List with 2 elements]
   |  ||  |  []> [15, 5] DeclField
   |  ||  |  ||  +> [15, 1] Identifier (int)
   |  ||  |  ||  |  definition: type defined at [0, 0], type=int
   |  ||  |  ||  +> [15, 5] Identifier (x)
   |  ||  |  ||  |  definition: field defined at [15, 5], type=int
   |  ||  |  ||  `> [15, 7] Initialization
   |  ||  |  ||     `> [15, 9] Int (5)
   |  ||  |  ||        type: int
   |  ||  |  []> [16, 7] DeclField
   |  ||  |      +> [16, 1] Identifier (float)
   |  ||  |      |  definition: type defined at [0, 0], type=float
   |  ||  |      +> [16, 7] Identifier (y)
   |  ||  |      |  definition: field defined at [16, 7], type=float
   |  ||  |      `> NoInitialization
   |  ||  `> ListDeclMethod [List with 2 elements]
   |  ||     []> [17, 1] DeclMethod
   |  ||     ||  +> [17, 1] Identifier (int)
   |  ||     ||  |  definition: type defined at [0, 0], type=int
   |  ||     ||  +> [17, 5] Identifier (getX)
   |  ||     ||  |  definition: method defined at [17, 1], type=int
   |  ||     ||  +> ListDeclParam [List with 0 elements]
   |  ||     ||  `> [17, 11] MethodBody
   |  ||     ||     +> ListDeclVar [List with 0 elements]
   |  ||     ||     `> ListInst [List with 1 elements]
   |  ||     ||        []> [18, 2] Return
   |  ||     ||            `> [18, 9] Identifier (x)
   |  ||     ||               definition: field defined at [15, 5], type=int
   |  ||     []> [20, 1] DeclMethod
   |  ||         +> [20, 1] Identifier (float)
   |  ||         |  definition: type defined at [0, 0], type=float
   |  ||         +> [20, 7] Identifier (getY)
   |  ||         |  definition: method defined at [20, 1], type=float
   |  ||         +> ListDeclParam [List with 0 elements]
   |  ||         `> [20, 13] MethodBody
   |  ||            +> ListDeclVar [List with 0 elements]
   |  ||            `> ListInst [List with 1 elements]
   |  ||               []> [21, 2] Return
   |  ||                   `> [21, 9] Identifier (y)
   |  ||                      definition: field defined at [16, 7], type=float
   |  []> [24, 0] DeclClass
   |      +> [24, 6] Identifier (B)
   |      |  definition: type defined at [24, 0], type=B
   |      +> [24, 8] Identifier (A)
   |      |  definition: type defined at [14, 0], type=A
   |      +> [25, 5] ListDeclField [List with 1 elements]
   |      |  []> [25, 5] DeclField
   |      |      +> [25, 1] Identifier (int)
   |      |      |  definition: type defined at [0, 0], type=int
   |      |      +> [25, 5] Identifier (z)
   |      |      |  definition: field defined at [25, 5], type=int
   |      |      `> NoInitialization
   |      `> ListDeclMethod [List with 1 elements]
   |         []> [26, 1] DeclMethod
   |             +> [26, 1] Identifier (int)
   |             |  definition: type defined at [0, 0], type=int
   |             +> [26, 5] Identifier (getZ)
   |             |  definition: method defined at [26, 1], type=int
   |             +> ListDeclParam [List with 0 elements]
   |             `> [26, 11] MethodBody
   |                +> ListDeclVar [List with 0 elements]
   |                `> ListInst [List with 1 elements]
   |                   []> [27, 2] Return
   |                       `> [27, 9] Identifier (z)
   |                          definition: field defined at [25, 5], type=int
   `> [30, 0] Main
      +> [32, 3] ListDeclVar [List with 2 elements]
      |  []> [31, 3] DeclVar
      |  ||  +> [31, 1] Identifier (A)
      |  ||  |  definition: type defined at [14, 0], type=A
      |  ||  +> [31, 3] Identifier (a)
      |  ||  |  definition: variable defined at [31, 3], type=A
      |  ||  `> [31, 5] Initialization
      |  ||     `> [31, 7] New
      |  ||        type: A
      |  ||        `> [31, 11] Identifier (A)
      |  ||           definition: type defined at [14, 0], type=A
      |  []> [32, 3] DeclVar
      |      +> [32, 1] Identifier (B)
      |      |  definition: type defined at [24, 0], type=B
      |      +> [32, 3] Identifier (b)
      |      |  definition: variable defined at [32, 3], type=B
      |      `> [32, 5] Initialization
      |         `> [32, 7] New
      |            type: B
      |            `> [32, 11] Identifier (B)
      |               definition: type defined at [24, 0], type=B
      `> ListInst [List with 1 elements]
         []> [33, 1] Println
             `> [33, 9] ListExpr [List with 1 elements]
                []> [33, 9] MethodCall
                    type: int
                    +> [33, 9] Identifier (b)
                    |  definition: variable defined at [32, 3], type=B
                    +> [33, 11] Identifier (getX)
                    |  definition: method defined at [17, 1], type=int
                    `> ListExpr [List with 0 elements]
