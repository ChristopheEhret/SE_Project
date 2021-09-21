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
      +> [16, 9] ListDeclVar [List with 2 elements]
      |  []> [16, 9] DeclVar
      |  ||  +> [16, 1] Identifier (boolean)
      |  ||  |  definition: type defined at [0, 0], type=boolean
      |  ||  +> [16, 9] Identifier (t)
      |  ||  |  definition: variable defined at [16, 9], type=boolean
      |  ||  `> [16, 10] Initialization
      |  ||     `> [16, 11] BooleanLiteral (true)
      |  ||        type: boolean
      |  []> [16, 17] DeclVar
      |      +> [16, 1] Identifier (boolean)
      |      |  definition: type defined at [0, 0], type=boolean
      |      +> [16, 17] Identifier (f)
      |      |  definition: variable defined at [16, 17], type=boolean
      |      `> [16, 19] Initialization
      |         `> [16, 21] BooleanLiteral (false)
      |            type: boolean
      `> ListInst [List with 15 elements]
         []> [18, 1] Not
         ||  type: boolean
         ||  `> [18, 2] Identifier (t)
         ||     definition: variable defined at [16, 9], type=boolean
         []> [19, 1] Not
         ||  type: boolean
         ||  `> [19, 2] Identifier (f)
         ||     definition: variable defined at [16, 17], type=boolean
         []> [20, 1] Or
         ||  type: boolean
         ||  +> [20, 1] Identifier (t)
         ||  |  definition: variable defined at [16, 9], type=boolean
         ||  `> [20, 6] Identifier (f)
         ||     definition: variable defined at [16, 17], type=boolean
         []> [21, 1] Or
         ||  type: boolean
         ||  +> [21, 1] Identifier (f)
         ||  |  definition: variable defined at [16, 17], type=boolean
         ||  `> [21, 6] Identifier (f)
         ||     definition: variable defined at [16, 17], type=boolean
         []> [22, 1] And
         ||  type: boolean
         ||  +> [22, 1] Identifier (t)
         ||  |  definition: variable defined at [16, 9], type=boolean
         ||  `> [22, 6] Identifier (t)
         ||     definition: variable defined at [16, 9], type=boolean
         []> [23, 1] And
         ||  type: boolean
         ||  +> [23, 1] Identifier (f)
         ||  |  definition: variable defined at [16, 17], type=boolean
         ||  `> [23, 6] Identifier (t)
         ||     definition: variable defined at [16, 9], type=boolean
         []> [24, 1] Equals
         ||  type: boolean
         ||  +> [24, 1] Int (1)
         ||  |  type: int
         ||  `> [24, 4] Int (2)
         ||     type: int
         []> [25, 1] NotEquals
         ||  type: boolean
         ||  +> [25, 1] Int (1)
         ||  |  type: int
         ||  `> [25, 4] Int (2)
         ||     type: int
         []> [26, 1] Lower
         ||  type: boolean
         ||  +> [26, 1] Int (1)
         ||  |  type: int
         ||  `> [26, 3] Int (2)
         ||     type: int
         []> [27, 1] Greater
         ||  type: boolean
         ||  +> [27, 1] Int (1)
         ||  |  type: int
         ||  `> [27, 3] Int (2)
         ||     type: int
         []> [28, 1] LowerOrEqual
         ||  type: boolean
         ||  +> [28, 1] Int (1)
         ||  |  type: int
         ||  `> [28, 4] Int (2)
         ||     type: int
         []> [29, 1] GreaterOrEqual
         ||  type: boolean
         ||  +> [29, 1] Int (1)
         ||  |  type: int
         ||  `> [29, 4] Int (2)
         ||     type: int
         []> [30, 1] Equals
         ||  type: boolean
         ||  +> [30, 1] Identifier (t)
         ||  |  definition: variable defined at [16, 9], type=boolean
         ||  `> [30, 4] Identifier (f)
         ||     definition: variable defined at [16, 17], type=boolean
         []> [31, 1] NotEquals
         ||  type: boolean
         ||  +> [31, 1] Identifier (f)
         ||  |  definition: variable defined at [16, 17], type=boolean
         ||  `> [31, 4] Identifier (f)
         ||     definition: variable defined at [16, 17], type=boolean
         []> [33, 1] Or
             type: boolean
             +> [33, 1] Identifier (f)
             |  definition: variable defined at [16, 17], type=boolean
             `> [33, 6] And
                type: boolean
                +> [33, 8] Identifier (t)
                |  definition: variable defined at [16, 9], type=boolean
                `> [33, 13] Not
                   type: boolean
                   `> [33, 14] Identifier (f)
                      definition: variable defined at [16, 17], type=boolean
