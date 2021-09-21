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
      +> [17, 7] ListDeclVar [List with 4 elements]
      |  []> [16, 5] DeclVar
      |  ||  +> [16, 1] Identifier (int)
      |  ||  |  definition: type defined at [0, 0], type=int
      |  ||  +> [16, 5] Identifier (i)
      |  ||  |  definition: variable defined at [16, 5], type=int
      |  ||  `> NoInitialization
      |  []> [16, 8] DeclVar
      |  ||  +> [16, 1] Identifier (int)
      |  ||  |  definition: type defined at [0, 0], type=int
      |  ||  +> [16, 8] Identifier (j)
      |  ||  |  definition: variable defined at [16, 8], type=int
      |  ||  `> NoInitialization
      |  []> [17, 7] DeclVar
      |  ||  +> [17, 1] Identifier (float)
      |  ||  |  definition: type defined at [0, 0], type=float
      |  ||  +> [17, 7] Identifier (f)
      |  ||  |  definition: variable defined at [17, 7], type=float
      |  ||  `> NoInitialization
      |  []> [17, 10] DeclVar
      |      +> [17, 1] Identifier (float)
      |      |  definition: type defined at [0, 0], type=float
      |      +> [17, 10] Identifier (g)
      |      |  definition: variable defined at [17, 10], type=float
      |      `> NoInitialization
      `> ListInst [List with 7 elements]
         []> [19, 1] Plus
         ||  type: int
         ||  +> [19, 1] Identifier (i)
         ||  |  definition: variable defined at [16, 5], type=int
         ||  `> [19, 5] Identifier (j)
         ||     definition: variable defined at [16, 8], type=int
         []> [20, 1] Minus
         ||  type: float
         ||  +> [20, 1] Identifier (f)
         ||  |  definition: variable defined at [17, 7], type=float
         ||  `> ConvFloat
         ||     type: float
         ||     `> [20, 5] Identifier (i)
         ||        definition: variable defined at [16, 5], type=int
         []> [21, 1] Multiply
         ||  type: float
         ||  +> ConvFloat
         ||  |  type: float
         ||  |  `> [21, 1] Identifier (j)
         ||  |     definition: variable defined at [16, 8], type=int
         ||  `> [21, 5] Identifier (f)
         ||     definition: variable defined at [17, 7], type=float
         []> [22, 1] Divide
         ||  type: float
         ||  +> [22, 1] Identifier (g)
         ||  |  definition: variable defined at [17, 10], type=float
         ||  `> [22, 5] Identifier (f)
         ||     definition: variable defined at [17, 7], type=float
         []> [23, 1] UnaryMinus
         ||  type: int
         ||  `> [23, 2] Identifier (i)
         ||     definition: variable defined at [16, 5], type=int
         []> [24, 1] UnaryMinus
         ||  type: float
         ||  `> [24, 2] Identifier (g)
         ||     definition: variable defined at [17, 10], type=float
         []> [26, 1] Modulo
             type: int
             +> [26, 1] Identifier (i)
             |  definition: variable defined at [16, 5], type=int
             `> [26, 5] Identifier (j)
                definition: variable defined at [16, 8], type=int
