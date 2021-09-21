`> [14, 0] Program
   +> ListDeclClass [List with 1 elements]
   |  []> [0, 0] DeclClass
   |      +> [0, 0] Identifier (Object)
   |      |  definition: type defined at [0, 0], type=Object
   |      +> [0, 0] Identifier (0)
   |      |  definition: type defined at [0, 0], type=0
   |      +> ListDeclField [List with 0 elements]
   |      `> [0, 0] ListDeclMethod [List with 1 elements]
   |         []> [0, 0] DeclMethod
   |             +> [0, 0] Identifier (boolean)
   |             |  definition: type defined at [0, 0], type=boolean
   |             +> [0, 0] Identifier (equals)
   |             |  definition: method defined at [0, 0], type=boolean
   |             +> [0, 0] ListDeclParam [List with 1 elements]
   |             |  []> [0, 0] DeclParam
   |             |      +> [0, 0] Identifier (Object)
   |             |      |  definition: type defined at [0, 0], type=Object
   |             |      `> [0, 0] Identifier (o)
   |             |         definition: parameter defined at [0, 0], type=Object
   |             `> [0, 0] MethodBody
   |                +> ListDeclVar [List with 0 elements]
   |                `> [0, 0] ListInst [List with 1 elements]
   |                   []> [0, 0] Return
   |                       `> [0, 0] Equals
   |                          type: boolean
   |                          +> [0, 0] This
   |                          |  type: Object
   |                          `> [0, 0] Identifier (o)
   |                             definition: parameter defined at [0, 0], type=Object
   `> [14, 0] Main
      +> [18, 7] ListDeclVar [List with 5 elements]
      |  []> [16, 5] DeclVar
      |  ||  +> [16, 1] Identifier (int)
      |  ||  |  definition: type defined at [0, 0], type=int
      |  ||  +> [16, 5] Identifier (b1)
      |  ||  |  definition: variable defined at [16, 5], type=int
      |  ||  `> NoInitialization
      |  []> [16, 9] DeclVar
      |  ||  +> [16, 1] Identifier (int)
      |  ||  |  definition: type defined at [0, 0], type=int
      |  ||  +> [16, 9] Identifier (b2)
      |  ||  |  definition: variable defined at [16, 9], type=int
      |  ||  `> NoInitialization
      |  []> [17, 9] DeclVar
      |  ||  +> [17, 1] Identifier (boolean)
      |  ||  |  definition: type defined at [0, 0], type=boolean
      |  ||  +> [17, 9] Identifier (c1)
      |  ||  |  definition: variable defined at [17, 9], type=boolean
      |  ||  `> NoInitialization
      |  []> [17, 13] DeclVar
      |  ||  +> [17, 1] Identifier (boolean)
      |  ||  |  definition: type defined at [0, 0], type=boolean
      |  ||  +> [17, 13] Identifier (c2)
      |  ||  |  definition: variable defined at [17, 13], type=boolean
      |  ||  `> NoInitialization
      |  []> [18, 7] DeclVar
      |      +> [18, 1] Identifier (float)
      |      |  definition: type defined at [0, 0], type=float
      |      +> [18, 7] Identifier (d)
      |      |  definition: variable defined at [18, 7], type=float
      |      `> NoInitialization
      `> ListInst [List with 5 elements]
         []> [20, 1] Assign
         ||  type: boolean
         ||  +> [20, 1] Identifier (c1)
         ||  |  definition: variable defined at [17, 9], type=boolean
         ||  `> [20, 6] BooleanLiteral (true)
         ||     type: boolean
         []> [21, 1] Assign
         ||  type: boolean
         ||  +> [21, 1] Identifier (c2)
         ||  |  definition: variable defined at [17, 13], type=boolean
         ||  `> [21, 6] Identifier (c1)
         ||     definition: variable defined at [17, 9], type=boolean
         []> [23, 1] Assign
         ||  type: int
         ||  +> [23, 1] Identifier (b2)
         ||  |  definition: variable defined at [16, 9], type=int
         ||  `> [23, 6] Identifier (b1)
         ||     definition: variable defined at [16, 5], type=int
         []> [24, 1] Assign
         ||  type: float
         ||  +> [24, 1] Identifier (d)
         ||  |  definition: variable defined at [18, 7], type=float
         ||  `> ConvFloat
         ||     type: float
         ||     `> [24, 5] Int (0)
         ||        type: int
         []> [25, 1] Assign
             type: float
             +> [25, 1] Identifier (d)
             |  definition: variable defined at [18, 7], type=float
             `> ConvFloat
                type: float
                `> [25, 5] Identifier (b2)
                   definition: variable defined at [16, 9], type=int
