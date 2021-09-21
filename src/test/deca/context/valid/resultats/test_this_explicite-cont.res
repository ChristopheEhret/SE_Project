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
   |      +> [15, 15] ListDeclField [List with 1 elements]
   |      |  []> [15, 15] DeclField
   |      |      +> [15, 11] Identifier (int)
   |      |      |  definition: type defined at [0, 0], type=int
   |      |      +> [15, 15] Identifier (x)
   |      |      |  definition: field defined at [15, 15], type=int
   |      |      `> [15, 17] Initialization
   |      |         `> [15, 19] Int (5)
   |      |            type: int
   |      `> ListDeclMethod [List with 1 elements]
   |         []> [16, 1] DeclMethod
   |             +> [16, 1] Identifier (int)
   |             |  definition: type defined at [0, 0], type=int
   |             +> [16, 5] Identifier (getX)
   |             |  definition: method defined at [16, 1], type=int
   |             +> ListDeclParam [List with 0 elements]
   |             `> [16, 11] MethodBody
   |                +> ListDeclVar [List with 0 elements]
   |                `> ListInst [List with 1 elements]
   |                   []> [17, 2] Return
   |                       `> [17, 9] Selection
   |                          type: int
   |                          +> [17, 9] This
   |                          |  type: A
   |                          `> [17, 14] Identifier (x)
   |                             definition: field defined at [15, 15], type=int
   `> [20, 0] Main
      +> [22, 5] ListDeclVar [List with 2 elements]
      |  []> [21, 3] DeclVar
      |  ||  +> [21, 1] Identifier (A)
      |  ||  |  definition: type defined at [14, 0], type=A
      |  ||  +> [21, 3] Identifier (a)
      |  ||  |  definition: variable defined at [21, 3], type=A
      |  ||  `> [21, 5] Initialization
      |  ||     `> [21, 7] New
      |  ||        type: A
      |  ||        `> [21, 11] Identifier (A)
      |  ||           definition: type defined at [14, 0], type=A
      |  []> [22, 5] DeclVar
      |      +> [22, 1] Identifier (int)
      |      |  definition: type defined at [0, 0], type=int
      |      +> [22, 5] Identifier (b)
      |      |  definition: variable defined at [22, 5], type=int
      |      `> [22, 7] Initialization
      |         `> [22, 9] MethodCall
      |            type: int
      |            +> [22, 9] Identifier (a)
      |            |  definition: variable defined at [21, 3], type=A
      |            +> [22, 11] Identifier (getX)
      |            |  definition: method defined at [16, 1], type=int
      |            `> ListExpr [List with 0 elements]
      `> ListInst [List with 1 elements]
         []> [23, 1] Println
             `> [23, 9] ListExpr [List with 1 elements]
                []> [23, 9] Identifier (b)
                    definition: variable defined at [22, 5], type=int
