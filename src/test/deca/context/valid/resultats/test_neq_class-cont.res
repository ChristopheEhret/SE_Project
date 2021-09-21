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
   |      |      `> [15, 7] Initialization
   |      |         `> [15, 9] Int (5)
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
   |                       `> [17, 9] Identifier (x)
   |                          definition: field defined at [15, 5], type=int
   `> [21, 0] Main
      +> [23, 9] ListDeclVar [List with 2 elements]
      |  []> [22, 3] DeclVar
      |  ||  +> [22, 1] Identifier (A)
      |  ||  |  definition: type defined at [14, 0], type=A
      |  ||  +> [22, 3] Identifier (a)
      |  ||  |  definition: variable defined at [22, 3], type=A
      |  ||  `> NoInitialization
      |  []> [23, 9] DeclVar
      |      +> [23, 1] Identifier (boolean)
      |      |  definition: type defined at [0, 0], type=boolean
      |      +> [23, 9] Identifier (bol)
      |      |  definition: variable defined at [23, 9], type=boolean
      |      `> [23, 13] Initialization
      |         `> [23, 15] NotEquals
      |            type: boolean
      |            +> [23, 15] Identifier (a)
      |            |  definition: variable defined at [22, 3], type=A
      |            `> [23, 18] Identifier (a)
      |               definition: variable defined at [22, 3], type=A
      `> ListInst [List with 0 elements]
