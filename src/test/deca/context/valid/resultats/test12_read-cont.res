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
      +> [16, 7] ListDeclVar [List with 2 elements]
      |  []> [15, 5] DeclVar
      |  ||  +> [15, 1] Identifier (int)
      |  ||  |  definition: type defined at [0, 0], type=int
      |  ||  +> [15, 5] Identifier (x)
      |  ||  |  definition: variable defined at [15, 5], type=int
      |  ||  `> NoInitialization
      |  []> [16, 7] DeclVar
      |      +> [16, 1] Identifier (float)
      |      |  definition: type defined at [0, 0], type=float
      |      +> [16, 7] Identifier (f)
      |      |  definition: variable defined at [16, 7], type=float
      |      `> NoInitialization
      `> ListInst [List with 3 elements]
         []> [18, 1] Assign
         ||  type: int
         ||  +> [18, 1] Identifier (x)
         ||  |  definition: variable defined at [15, 5], type=int
         ||  `> [18, 3] ReadInt
         ||     type: int
         []> [19, 1] Assign
         ||  type: float
         ||  +> [19, 1] Identifier (f)
         ||  |  definition: variable defined at [16, 7], type=float
         ||  `> [19, 3] UnaryMinus
         ||     type: float
         ||     `> [19, 4] ReadFloat
         ||        type: float
         []> [20, 1] Assign
             type: float
             +> [20, 1] Identifier (f)
             |  definition: variable defined at [16, 7], type=float
             `> ConvFloat
                type: float
                `> [20, 3] ReadInt
                   type: int
