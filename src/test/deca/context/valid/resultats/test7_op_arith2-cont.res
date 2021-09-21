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
      +> [18, 12] ListDeclVar [List with 6 elements]
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
      |  ||  +> [17, 1] Identifier (float)
      |  ||  |  definition: type defined at [0, 0], type=float
      |  ||  +> [17, 10] Identifier (g)
      |  ||  |  definition: variable defined at [17, 10], type=float
      |  ||  `> NoInitialization
      |  []> [18, 12] DeclVar
      |  ||  +> [18, 4] Identifier (boolean)
      |  ||  |  definition: type defined at [0, 0], type=boolean
      |  ||  +> [18, 12] Identifier (a)
      |  ||  |  definition: variable defined at [18, 12], type=boolean
      |  ||  `> [18, 13] Initialization
      |  ||     `> [18, 14] BooleanLiteral (true)
      |  ||        type: boolean
      |  []> [18, 20] DeclVar
      |      +> [18, 4] Identifier (boolean)
      |      |  definition: type defined at [0, 0], type=boolean
      |      +> [18, 20] Identifier (b)
      |      |  definition: variable defined at [18, 20], type=boolean
      |      `> [18, 21] Initialization
      |         `> [18, 22] BooleanLiteral (false)
      |            type: boolean
      `> ListInst [List with 10 elements]
         []> [20, 1] Assign
         ||  type: int
         ||  +> [20, 1] Identifier (i)
         ||  |  definition: variable defined at [16, 5], type=int
         ||  `> [20, 5] Plus
         ||     type: int
         ||     +> [20, 6] Int (1)
         ||     |  type: int
         ||     `> [20, 8] Plus
         ||        type: int
         ||        +> [20, 9] Int (1)
         ||        |  type: int
         ||        `> [20, 11] Plus
         ||           type: int
         ||           +> [20, 12] Int (1)
         ||           |  type: int
         ||           `> [20, 14] Plus
         ||              type: int
         ||              +> [20, 15] Int (1)
         ||              |  type: int
         ||              `> [20, 17] Plus
         ||                 type: int
         ||                 +> [20, 18] Int (1)
         ||                 |  type: int
         ||                 `> [20, 20] Plus
         ||                    type: int
         ||                    +> [20, 21] Int (1)
         ||                    |  type: int
         ||                    `> [20, 23] Plus
         ||                       type: int
         ||                       +> [20, 24] Int (1)
         ||                       |  type: int
         ||                       `> [20, 26] Minus
         ||                          type: int
         ||                          +> [20, 27] Int (1)
         ||                          |  type: int
         ||                          `> [20, 29] Minus
         ||                             type: int
         ||                             +> [20, 30] Int (1)
         ||                             |  type: int
         ||                             `> [20, 32] Minus
         ||                                type: int
         ||                                +> [20, 33] Int (1)
         ||                                |  type: int
         ||                                `> [20, 35] Minus
         ||                                   type: int
         ||                                   +> [20, 36] Int (1)
         ||                                   |  type: int
         ||                                   `> [20, 38] Minus
         ||                                      type: int
         ||                                      +> [20, 39] Int (1)
         ||                                      |  type: int
         ||                                      `> [20, 41] Multiply
         ||                                         type: int
         ||                                         +> [20, 42] Int (1)
         ||                                         |  type: int
         ||                                         `> [20, 44] Multiply
         ||                                            type: int
         ||                                            +> [20, 45] Int (1)
         ||                                            |  type: int
         ||                                            `> [20, 47] Multiply
         ||                                               type: int
         ||                                               +> [20, 48] Int (1)
         ||                                               |  type: int
         ||                                               `> [20, 50] Multiply
         ||                                                  type: int
         ||                                                  +> [20, 51] Int (1)
         ||                                                  |  type: int
         ||                                                  `> [20, 53] Plus
         ||                                                     type: int
         ||                                                     +> [20, 54] Int (1)
         ||                                                     |  type: int
         ||                                                     `> [20, 56] Plus
         ||                                                        type: int
         ||                                                        +> [20, 57] Int (1)
         ||                                                        |  type: int
         ||                                                        `> [20, 59] Plus
         ||                                                           type: int
         ||                                                           +> [20, 60] Int (1)
         ||                                                           |  type: int
         ||                                                           `> [20, 62] Plus
         ||                                                              type: int
         ||                                                              +> [20, 63] Int (1)
         ||                                                              |  type: int
         ||                                                              `> [20, 65] Plus
         ||                                                                 type: int
         ||                                                                 +> [20, 66] Int (1)
         ||                                                                 |  type: int
         ||                                                                 `> [20, 68] Plus
         ||                                                                    type: int
         ||                                                                    +> [20, 69] Int (1)
         ||                                                                    |  type: int
         ||                                                                    `> [20, 71] Plus
         ||                                                                       type: int
         ||                                                                       +> [20, 72] Int (1)
         ||                                                                       |  type: int
         ||                                                                       `> [20, 74] Plus
         ||                                                                          type: int
         ||                                                                          +> [20, 75] Int (1)
         ||                                                                          |  type: int
         ||                                                                          `> [20, 77] Plus
         ||                                                                             type: int
         ||                                                                             +> [20, 78] Int (1)
         ||                                                                             |  type: int
         ||                                                                             `> [20, 80] Plus
         ||                                                                                type: int
         ||                                                                                +> [20, 81] Int (1)
         ||                                                                                |  type: int
         ||                                                                                `> [20, 83] Plus
         ||                                                                                   type: int
         ||                                                                                   +> [20, 84] Int (1)
         ||                                                                                   |  type: int
         ||                                                                                   `> [20, 86] Plus
         ||                                                                                      type: int
         ||                                                                                      +> [20, 87] Int (1)
         ||                                                                                      |  type: int
         ||                                                                                      `> [20, 89] Plus
         ||                                                                                         type: int
         ||                                                                                         +> [20, 90] Int (1)
         ||                                                                                         |  type: int
         ||                                                                                         `> [20, 92] Plus
         ||                                                                                            type: int
         ||                                                                                            +> [20, 93] Int (1)
         ||                                                                                            |  type: int
         ||                                                                                            `> [20, 95] Modulo
         ||                                                                                               type: int
         ||                                                                                               +> [20, 96] Int (1)
         ||                                                                                               |  type: int
         ||                                                                                               `> [20, 98] Divide
         ||                                                                                                  type: int
         ||                                                                                                  +> [20, 99] Int (1)
         ||                                                                                                  |  type: int
         ||                                                                                                  `> [20, 101] Divide
         ||                                                                                                     type: int
         ||                                                                                                     +> [20, 102] Int (1)
         ||                                                                                                     |  type: int
         ||                                                                                                     `> [20, 104] Divide
         ||                                                                                                        type: int
         ||                                                                                                        +> [20, 105] Int (1)
         ||                                                                                                        |  type: int
         ||                                                                                                        `> [20, 107] Multiply
         ||                                                                                                           type: int
         ||                                                                                                           +> [20, 108] Int (1)
         ||                                                                                                           |  type: int
         ||                                                                                                           `> [20, 110] Int (1)
         ||                                                                                                              type: int
         []> [21, 4] Assign
         ||  type: int
         ||  +> [21, 4] Identifier (j)
         ||  |  definition: variable defined at [16, 8], type=int
         ||  `> [21, 8] Plus
         ||     type: int
         ||     +> [21, 9] Plus
         ||     |  type: int
         ||     |  +> [21, 10] Plus
         ||     |  |  type: int
         ||     |  |  +> [21, 11] Plus
         ||     |  |  |  type: int
         ||     |  |  |  +> [21, 12] Plus
         ||     |  |  |  |  type: int
         ||     |  |  |  |  +> [21, 13] Plus
         ||     |  |  |  |  |  type: int
         ||     |  |  |  |  |  +> [21, 14] Plus
         ||     |  |  |  |  |  |  type: int
         ||     |  |  |  |  |  |  +> [21, 15] Plus
         ||     |  |  |  |  |  |  |  type: int
         ||     |  |  |  |  |  |  |  +> [21, 16] Plus
         ||     |  |  |  |  |  |  |  |  type: int
         ||     |  |  |  |  |  |  |  |  +> [21, 17] Plus
         ||     |  |  |  |  |  |  |  |  |  type: int
         ||     |  |  |  |  |  |  |  |  |  +> [21, 18] Plus
         ||     |  |  |  |  |  |  |  |  |  |  type: int
         ||     |  |  |  |  |  |  |  |  |  |  +> [21, 19] Plus
         ||     |  |  |  |  |  |  |  |  |  |  |  type: int
         ||     |  |  |  |  |  |  |  |  |  |  |  +> [21, 20] Plus
         ||     |  |  |  |  |  |  |  |  |  |  |  |  type: int
         ||     |  |  |  |  |  |  |  |  |  |  |  |  +> [21, 21] Plus
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  type: int
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  +> [21, 22] Plus
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  type: int
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  +> [21, 23] Plus
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  type: int
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  +> [21, 24] Minus
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  type: int
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  +> [21, 25] Minus
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  type: int
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  +> [21, 26] Minus
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  type: int
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  +> [21, 27] Minus
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  type: int
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  +> [21, 28] Minus
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  type: int
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  +> [21, 29] Minus
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  type: int
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  +> [21, 30] Minus
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  type: int
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  +> [21, 31] Minus
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  type: int
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  +> [21, 32] Modulo
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  type: int
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  +> [21, 33] Multiply
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  type: int
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  +> [21, 34] Multiply
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  type: int
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  +> [21, 35] Multiply
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  type: int
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  +> [21, 36] Multiply
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  type: int
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  +> [21, 37] Multiply
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  type: int
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  +> [21, 38] Multiply
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  type: int
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  +> [21, 39] Divide
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  type: int
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  +> [21, 40] Divide
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  type: int
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  +> [21, 41] Divide
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  type: int
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  +> [21, 42] Multiply
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  type: int
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  +> [21, 43] Int (1)
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  type: int
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  `> [21, 45] Int (1)
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |     type: int
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  `> [21, 48] Int (1)
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |     type: int
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  `> [21, 51] Int (1)
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |     type: int
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  `> [21, 54] Int (1)
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |     type: int
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  `> [21, 57] Int (1)
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |     type: int
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  `> [21, 60] Int (1)
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |     type: int
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  `> [21, 63] Int (1)
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |     type: int
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  `> [21, 66] Int (1)
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |     type: int
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  `> [21, 69] Int (1)
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |     type: int
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  `> [21, 72] Int (1)
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |     type: int
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  `> [21, 75] Int (1)
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |     type: int
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  `> [21, 78] Int (1)
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |     type: int
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  `> [21, 81] Int (1)
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |     type: int
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  `> [21, 84] Int (1)
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |     type: int
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  `> [21, 87] Int (1)
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |     type: int
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  `> [21, 90] Int (1)
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |     type: int
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  `> [21, 93] Int (1)
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |     type: int
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  `> [21, 96] Int (1)
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |     type: int
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  `> [21, 99] Int (1)
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |     type: int
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  `> [21, 102] Int (1)
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |     type: int
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  `> [21, 105] Int (1)
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |     type: int
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  `> [21, 108] Int (1)
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |     type: int
         ||     |  |  |  |  |  |  |  |  |  |  |  |  `> [21, 111] Int (1)
         ||     |  |  |  |  |  |  |  |  |  |  |  |     type: int
         ||     |  |  |  |  |  |  |  |  |  |  |  `> [21, 114] Int (1)
         ||     |  |  |  |  |  |  |  |  |  |  |     type: int
         ||     |  |  |  |  |  |  |  |  |  |  `> [21, 117] Int (1)
         ||     |  |  |  |  |  |  |  |  |  |     type: int
         ||     |  |  |  |  |  |  |  |  |  `> [21, 120] Int (1)
         ||     |  |  |  |  |  |  |  |  |     type: int
         ||     |  |  |  |  |  |  |  |  `> [21, 123] Int (1)
         ||     |  |  |  |  |  |  |  |     type: int
         ||     |  |  |  |  |  |  |  `> [21, 126] Int (1)
         ||     |  |  |  |  |  |  |     type: int
         ||     |  |  |  |  |  |  `> [21, 129] Int (1)
         ||     |  |  |  |  |  |     type: int
         ||     |  |  |  |  |  `> [21, 132] Int (1)
         ||     |  |  |  |  |     type: int
         ||     |  |  |  |  `> [21, 135] Int (1)
         ||     |  |  |  |     type: int
         ||     |  |  |  `> [21, 138] Int (1)
         ||     |  |  |     type: int
         ||     |  |  `> [21, 141] Int (1)
         ||     |  |     type: int
         ||     |  `> [21, 144] Int (1)
         ||     |     type: int
         ||     `> [21, 147] Int (1)
         ||        type: int
         []> [23, 4] Assign
         ||  type: float
         ||  +> [23, 4] Identifier (f)
         ||  |  definition: variable defined at [17, 7], type=float
         ||  `> [23, 8] Plus
         ||     type: float
         ||     +> [23, 9] Float (1.0)
         ||     |  type: float
         ||     `> [23, 13] Plus
         ||        type: float
         ||        +> [23, 14] Float (1.0)
         ||        |  type: float
         ||        `> [23, 18] Plus
         ||           type: float
         ||           +> [23, 19] Float (1.0)
         ||           |  type: float
         ||           `> [23, 23] Plus
         ||              type: float
         ||              +> [23, 24] Float (1.0)
         ||              |  type: float
         ||              `> [23, 28] Minus
         ||                 type: float
         ||                 +> [23, 29] Float (1.0)
         ||                 |  type: float
         ||                 `> [23, 33] Minus
         ||                    type: float
         ||                    +> [23, 34] Float (1.0)
         ||                    |  type: float
         ||                    `> [23, 38] Minus
         ||                       type: float
         ||                       +> [23, 39] Float (1.0)
         ||                       |  type: float
         ||                       `> [23, 43] Minus
         ||                          type: float
         ||                          +> [23, 44] Float (1.0)
         ||                          |  type: float
         ||                          `> [23, 48] Divide
         ||                             type: float
         ||                             +> [23, 49] Float (1.0)
         ||                             |  type: float
         ||                             `> [23, 53] Divide
         ||                                type: float
         ||                                +> [23, 54] Float (1.0)
         ||                                |  type: float
         ||                                `> [23, 58] Divide
         ||                                   type: float
         ||                                   +> [23, 59] Float (1.0)
         ||                                   |  type: float
         ||                                   `> [23, 63] Divide
         ||                                      type: float
         ||                                      +> [23, 64] Float (1.0)
         ||                                      |  type: float
         ||                                      `> [23, 68] Multiply
         ||                                         type: float
         ||                                         +> [23, 69] Float (1.0)
         ||                                         |  type: float
         ||                                         `> [23, 73] Multiply
         ||                                            type: float
         ||                                            +> [23, 74] Float (1.0)
         ||                                            |  type: float
         ||                                            `> [23, 78] Multiply
         ||                                               type: float
         ||                                               +> [23, 79] Float (1.0)
         ||                                               |  type: float
         ||                                               `> [23, 83] Float (1.0)
         ||                                                  type: float
         []> [24, 4] Assign
         ||  type: float
         ||  +> [24, 4] Identifier (g)
         ||  |  definition: variable defined at [17, 10], type=float
         ||  `> [24, 8] Plus
         ||     type: float
         ||     +> [24, 9] Plus
         ||     |  type: float
         ||     |  +> [24, 10] Plus
         ||     |  |  type: float
         ||     |  |  +> [24, 11] Plus
         ||     |  |  |  type: float
         ||     |  |  |  +> [24, 12] Plus
         ||     |  |  |  |  type: float
         ||     |  |  |  |  +> [24, 13] Plus
         ||     |  |  |  |  |  type: float
         ||     |  |  |  |  |  +> [24, 14] Minus
         ||     |  |  |  |  |  |  type: float
         ||     |  |  |  |  |  |  +> [24, 15] Minus
         ||     |  |  |  |  |  |  |  type: float
         ||     |  |  |  |  |  |  |  +> [24, 16] Minus
         ||     |  |  |  |  |  |  |  |  type: float
         ||     |  |  |  |  |  |  |  |  +> [24, 17] Minus
         ||     |  |  |  |  |  |  |  |  |  type: float
         ||     |  |  |  |  |  |  |  |  |  +> [24, 18] Divide
         ||     |  |  |  |  |  |  |  |  |  |  type: float
         ||     |  |  |  |  |  |  |  |  |  |  +> [24, 19] Divide
         ||     |  |  |  |  |  |  |  |  |  |  |  type: float
         ||     |  |  |  |  |  |  |  |  |  |  |  +> [24, 20] Multiply
         ||     |  |  |  |  |  |  |  |  |  |  |  |  type: float
         ||     |  |  |  |  |  |  |  |  |  |  |  |  +> [24, 21] Multiply
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  type: float
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  +> [24, 22] Multiply
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  type: float
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  +> [24, 23] Float (1.0)
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  type: float
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |  `> [24, 27] Float (1.0)
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  |     type: float
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |  `> [24, 32] Float (1.0)
         ||     |  |  |  |  |  |  |  |  |  |  |  |  |     type: float
         ||     |  |  |  |  |  |  |  |  |  |  |  |  `> [24, 37] Float (1.0)
         ||     |  |  |  |  |  |  |  |  |  |  |  |     type: float
         ||     |  |  |  |  |  |  |  |  |  |  |  `> [24, 42] Float (1.0)
         ||     |  |  |  |  |  |  |  |  |  |  |     type: float
         ||     |  |  |  |  |  |  |  |  |  |  `> [24, 47] Float (1.0)
         ||     |  |  |  |  |  |  |  |  |  |     type: float
         ||     |  |  |  |  |  |  |  |  |  `> [24, 52] Float (1.0)
         ||     |  |  |  |  |  |  |  |  |     type: float
         ||     |  |  |  |  |  |  |  |  `> [24, 57] Float (1.0)
         ||     |  |  |  |  |  |  |  |     type: float
         ||     |  |  |  |  |  |  |  `> [24, 62] Float (1.0)
         ||     |  |  |  |  |  |  |     type: float
         ||     |  |  |  |  |  |  `> [24, 67] Float (1.0)
         ||     |  |  |  |  |  |     type: float
         ||     |  |  |  |  |  `> [24, 72] Float (1.0)
         ||     |  |  |  |  |     type: float
         ||     |  |  |  |  `> [24, 77] Float (1.0)
         ||     |  |  |  |     type: float
         ||     |  |  |  `> [24, 82] Float (1.0)
         ||     |  |  |     type: float
         ||     |  |  `> [24, 87] Float (1.0)
         ||     |  |     type: float
         ||     |  `> [24, 92] Float (1.0)
         ||     |     type: float
         ||     `> [24, 97] Float (1.0)
         ||        type: float
         []> [26, 4] Assign
         ||  type: boolean
         ||  +> [26, 4] Identifier (a)
         ||  |  definition: variable defined at [18, 12], type=boolean
         ||  `> [26, 8] Or
         ||     type: boolean
         ||     +> [26, 9] Identifier (a)
         ||     |  definition: variable defined at [18, 12], type=boolean
         ||     `> [26, 12] Or
         ||        type: boolean
         ||        +> [26, 13] Identifier (b)
         ||        |  definition: variable defined at [18, 20], type=boolean
         ||        `> [26, 16] And
         ||           type: boolean
         ||           +> [26, 17] Identifier (a)
         ||           |  definition: variable defined at [18, 12], type=boolean
         ||           `> [26, 20] And
         ||              type: boolean
         ||              +> [26, 21] Identifier (b)
         ||              |  definition: variable defined at [18, 20], type=boolean
         ||              `> [26, 24] Equals
         ||                 type: boolean
         ||                 +> [26, 25] Identifier (a)
         ||                 |  definition: variable defined at [18, 12], type=boolean
         ||                 `> [26, 28] Equals
         ||                    type: boolean
         ||                    +> [26, 29] Identifier (b)
         ||                    |  definition: variable defined at [18, 20], type=boolean
         ||                    `> [26, 32] NotEquals
         ||                       type: boolean
         ||                       +> [26, 33] Identifier (a)
         ||                       |  definition: variable defined at [18, 12], type=boolean
         ||                       `> [26, 36] NotEquals
         ||                          type: boolean
         ||                          +> [26, 37] Identifier (b)
         ||                          |  definition: variable defined at [18, 20], type=boolean
         ||                          `> [26, 40] BooleanLiteral (true)
         ||                             type: boolean
         []> [27, 4] Assign
         ||  type: boolean
         ||  +> [27, 4] Identifier (b)
         ||  |  definition: variable defined at [18, 20], type=boolean
         ||  `> [27, 8] Or
         ||     type: boolean
         ||     +> [27, 8] Or
         ||     |  type: boolean
         ||     |  +> [27, 8] And
         ||     |  |  type: boolean
         ||     |  |  +> [27, 8] Equals
         ||     |  |  |  type: boolean
         ||     |  |  |  +> [27, 8] Identifier (i)
         ||     |  |  |  |  definition: variable defined at [16, 5], type=int
         ||     |  |  |  `> [27, 11] Identifier (j)
         ||     |  |  |     definition: variable defined at [16, 8], type=int
         ||     |  |  `> [27, 16] Lower
         ||     |  |     type: boolean
         ||     |  |     +> [27, 16] Identifier (i)
         ||     |  |     |  definition: variable defined at [16, 5], type=int
         ||     |  |     `> [27, 18] Identifier (j)
         ||     |  |        definition: variable defined at [16, 8], type=int
         ||     |  `> [27, 23] And
         ||     |     type: boolean
         ||     |     +> [27, 23] LowerOrEqual
         ||     |     |  type: boolean
         ||     |     |  +> [27, 23] Identifier (i)
         ||     |     |  |  definition: variable defined at [16, 5], type=int
         ||     |     |  `> [27, 26] Identifier (j)
         ||     |     |     definition: variable defined at [16, 8], type=int
         ||     |     `> [27, 31] Greater
         ||     |        type: boolean
         ||     |        +> [27, 31] Identifier (i)
         ||     |        |  definition: variable defined at [16, 5], type=int
         ||     |        `> [27, 33] Identifier (j)
         ||     |           definition: variable defined at [16, 8], type=int
         ||     `> [27, 38] And
         ||        type: boolean
         ||        +> [27, 38] GreaterOrEqual
         ||        |  type: boolean
         ||        |  +> [27, 38] Identifier (i)
         ||        |  |  definition: variable defined at [16, 5], type=int
         ||        |  `> [27, 41] Identifier (j)
         ||        |     definition: variable defined at [16, 8], type=int
         ||        `> [27, 46] NotEquals
         ||           type: boolean
         ||           +> [27, 46] Identifier (i)
         ||           |  definition: variable defined at [16, 5], type=int
         ||           `> [27, 49] Identifier (j)
         ||              definition: variable defined at [16, 8], type=int
         []> [29, 4] Println
         ||  `> [29, 12] ListExpr [List with 1 elements]
         ||     []> [29, 12] Identifier (i)
         ||         definition: variable defined at [16, 5], type=int
         []> [30, 4] Println
         ||  `> [30, 12] ListExpr [List with 1 elements]
         ||     []> [30, 12] Identifier (j)
         ||         definition: variable defined at [16, 8], type=int
         []> [31, 4] Println
         ||  `> [31, 12] ListExpr [List with 1 elements]
         ||     []> [31, 12] Identifier (f)
         ||         definition: variable defined at [17, 7], type=float
         []> [32, 4] Println
             `> [32, 12] ListExpr [List with 1 elements]
                []> [32, 12] Identifier (g)
                    definition: variable defined at [17, 10], type=float
