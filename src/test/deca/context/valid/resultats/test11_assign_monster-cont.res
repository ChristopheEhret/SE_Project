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
      |  []> [15, 7] DeclVar
      |  ||  +> [15, 1] Identifier (float)
      |  ||  |  definition: type defined at [0, 0], type=float
      |  ||  +> [15, 7] Identifier (x)
      |  ||  |  definition: variable defined at [15, 7], type=float
      |  ||  `> [15, 9] Initialization
      |  ||     `> [15, 11] Plus
      |  ||        type: float
      |  ||        +> [15, 11] Multiply
      |  ||        |  type: float
      |  ||        |  +> [15, 11] Divide
      |  ||        |  |  type: float
      |  ||        |  |  +> ConvFloat
      |  ||        |  |  |  type: float
      |  ||        |  |  |  `> [15, 11] Plus
      |  ||        |  |  |     type: int
      |  ||        |  |  |     +> [15, 12] Int (2)
      |  ||        |  |  |     |  type: int
      |  ||        |  |  |     `> [15, 16] Int (3)
      |  ||        |  |  |        type: int
      |  ||        |  |  `> [15, 21] Float (3.3)
      |  ||        |  |     type: float
      |  ||        |  `> ConvFloat
      |  ||        |     type: float
      |  ||        |     `> [15, 27] Int (7)
      |  ||        |        type: int
      |  ||        `> ConvFloat
      |  ||           type: float
      |  ||           `> [15, 31] Modulo
      |  ||              type: int
      |  ||              +> [15, 31] UnaryMinus
      |  ||              |  type: int
      |  ||              |  `> [15, 32] Int (3)
      |  ||              |     type: int
      |  ||              `> [15, 36] Int (3)
      |  ||                 type: int
      |  []> [16, 9] DeclVar
      |      +> [16, 1] Identifier (boolean)
      |      |  definition: type defined at [0, 0], type=boolean
      |      +> [16, 9] Identifier (b)
      |      |  definition: variable defined at [16, 9], type=boolean
      |      `> [16, 11] Initialization
      |         `> [16, 13] Or
      |            type: boolean
      |            +> [16, 13] BooleanLiteral (true)
      |            |  type: boolean
      |            `> [16, 21] And
      |               type: boolean
      |               +> [16, 21] BooleanLiteral (false)
      |               |  type: boolean
      |               `> [16, 30] NotEquals
      |                  type: boolean
      |                  +> [16, 30] Equals
      |                  |  type: boolean
      |                  |  +> [16, 30] Not
      |                  |  |  type: boolean
      |                  |  |  `> [16, 31] BooleanLiteral (false)
      |                  |  |     type: boolean
      |                  |  `> [16, 40] BooleanLiteral (true)
      |                  |     type: boolean
      |                  `> [16, 48] BooleanLiteral (false)
      |                     type: boolean
      `> ListInst [List with 0 elements]
