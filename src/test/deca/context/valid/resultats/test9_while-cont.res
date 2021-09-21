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
      +> [17, 9] ListDeclVar [List with 3 elements]
      |  []> [15, 5] DeclVar
      |  ||  +> [15, 1] Identifier (int)
      |  ||  |  definition: type defined at [0, 0], type=int
      |  ||  +> [15, 5] Identifier (x)
      |  ||  |  definition: variable defined at [15, 5], type=int
      |  ||  `> NoInitialization
      |  []> [16, 7] DeclVar
      |  ||  +> [16, 1] Identifier (float)
      |  ||  |  definition: type defined at [0, 0], type=float
      |  ||  +> [16, 7] Identifier (f)
      |  ||  |  definition: variable defined at [16, 7], type=float
      |  ||  `> NoInitialization
      |  []> [17, 9] DeclVar
      |      +> [17, 1] Identifier (boolean)
      |      |  definition: type defined at [0, 0], type=boolean
      |      +> [17, 9] Identifier (b)
      |      |  definition: variable defined at [17, 9], type=boolean
      |      `> NoInitialization
      `> ListInst [List with 2 elements]
         []> [19, 1] While
         ||  +> [19, 7] BooleanLiteral (true)
         ||  |  type: boolean
         ||  `> ListInst [List with 0 elements]
         []> [20, 1] While
             +> [20, 7] Greater
             |  type: boolean
             |  +> ConvFloat
             |  |  type: float
             |  |  `> [20, 7] Identifier (x)
             |  |     definition: variable defined at [15, 5], type=int
             |  `> [20, 11] Float (8.4)
             |     type: float
             `> ListInst [List with 1 elements]
                []> [22, 2] While
                    +> [22, 8] Or
                    |  type: boolean
                    |  +> [22, 8] Not
                    |  |  type: boolean
                    |  |  `> [22, 9] Identifier (b)
                    |  |     definition: variable defined at [17, 9], type=boolean
                    |  `> [22, 14] Greater
                    |     type: boolean
                    |     +> [22, 14] Identifier (f)
                    |     |  definition: variable defined at [16, 7], type=float
                    |     `> ConvFloat
                    |        type: float
                    |        `> [22, 18] Int (5)
                    |           type: int
                    `> ListInst [List with 2 elements]
                       []> [23, 3] Assign
                       ||  type: boolean
                       ||  +> [23, 3] Identifier (b)
                       ||  |  definition: variable defined at [17, 9], type=boolean
                       ||  `> [23, 7] Not
                       ||     type: boolean
                       ||     `> [23, 8] Identifier (b)
                       ||        definition: variable defined at [17, 9], type=boolean
                       []> [24, 3] Assign
                           type: int
                           +> [24, 3] Identifier (x)
                           |  definition: variable defined at [15, 5], type=int
                           `> [24, 7] UnaryMinus
                              type: int
                              `> [24, 8] Identifier (x)
                                 definition: variable defined at [15, 5], type=int
