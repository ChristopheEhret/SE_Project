`> [14, 0] Program
   +> [14, 0] ListDeclClass [List with 2 elements]
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
   |      +> [14, 6] Identifier (A)
   |      |  definition: type defined at [14, 0], type=A
   |      +> Identifier (Object)
   |      |  definition: type defined at [0, 0], type=Object
   |      +> [15, 5] ListDeclField [List with 1 elements]
   |      |  []> [15, 5] DeclField
   |      |      +> [15, 1] Identifier (int)
   |      |      |  definition: type defined at [0, 0], type=int
   |      |      +> [15, 5] Identifier (x)
   |      |      |  definition: field defined at [15, 5], type=int
   |      |      `> NoInitialization
   |      `> ListDeclMethod [List with 2 elements]
   |         []> [16, 1] DeclMethod
   |         ||  +> [16, 1] Identifier (int)
   |         ||  |  definition: type defined at [0, 0], type=int
   |         ||  +> [16, 5] Identifier (getX)
   |         ||  |  definition: method defined at [16, 1], type=int
   |         ||  +> ListDeclParam [List with 0 elements]
   |         ||  `> [16, 11] MethodBody
   |         ||     +> ListDeclVar [List with 0 elements]
   |         ||     `> ListInst [List with 1 elements]
   |         ||        []> [17, 2] Return
   |         ||            `> [17, 9] Identifier (x)
   |         ||               definition: field defined at [15, 5], type=int
   |         []> [19, 1] DeclMethod
   |             +> [19, 1] Identifier (float)
   |             |  definition: type defined at [0, 0], type=float
   |             +> [19, 7] Identifier (getY)
   |             |  definition: method defined at [19, 1], type=float
   |             +> ListDeclParam [List with 0 elements]
   |             `> [19, 13] MethodBody
   |                +> ListDeclVar [List with 0 elements]
   |                `> ListInst [List with 1 elements]
   |                   []> [20, 2] Return
   |                       `> ConvFloat
   |                          type: float
   |                          `> [20, 9] Identifier (x)
   |                             definition: field defined at [15, 5], type=int
   `> [23, 0] Main
      +> [25, 3] ListDeclVar [List with 2 elements]
      |  []> [24, 5] DeclVar
      |  ||  +> [24, 1] Identifier (int)
      |  ||  |  definition: type defined at [0, 0], type=int
      |  ||  +> [24, 5] Identifier (k)
      |  ||  |  definition: variable defined at [24, 5], type=int
      |  ||  `> [24, 7] Initialization
      |  ||     `> [24, 9] Int (21474836)
      |  ||        type: int
      |  []> [25, 3] DeclVar
      |      +> [25, 1] Identifier (A)
      |      |  definition: type defined at [14, 0], type=A
      |      +> [25, 3] Identifier (a)
      |      |  definition: variable defined at [25, 3], type=A
      |      `> [25, 5] Initialization
      |         `> [25, 7] New
      |            type: A
      |            `> [25, 11] Identifier (A)
      |               definition: type defined at [14, 0], type=A
      `> ListInst [List with 1 elements]
         []> [26, 1] Println
             `> [26, 9] ListExpr [List with 1 elements]
                []> [26, 9] StringLiteral (hello)
                    type: string
