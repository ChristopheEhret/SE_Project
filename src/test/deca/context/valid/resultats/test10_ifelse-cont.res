`> [15, 0] Program
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
   `> [15, 0] Main
      +> ListDeclVar [List with 0 elements]
      `> ListInst [List with 3 elements]
         []> [16, 1] IfThenElse
         ||  +> [16, 4] Not
         ||  |  type: boolean
         ||  |  `> [16, 5] Equals
         ||  |     type: boolean
         ||  |     +> [16, 6] Int (2)
         ||  |     |  type: int
         ||  |     `> [16, 11] Int (2)
         ||  |        type: int
         ||  +> ListInst [List with 1 elements]
         ||  |  []> [17, 2] Println
         ||  |      `> [17, 10] ListExpr [List with 1 elements]
         ||  |         []> [17, 10] StringLiteral (Hello 1)
         ||  |             type: string
         ||  `> ListInst [List with 0 elements]
         []> [20, 1] IfThenElse
         ||  +> [20, 4] Greater
         ||  |  type: boolean
         ||  |  +> [20, 4] Int (2)
         ||  |  |  type: int
         ||  |  `> [20, 8] Plus
         ||  |     type: int
         ||  |     +> [20, 8] Int (3)
         ||  |     |  type: int
         ||  |     `> [20, 10] Int (2)
         ||  |        type: int
         ||  +> ListInst [List with 1 elements]
         ||  |  []> [21, 2] Println
         ||  |      `> [21, 10] ListExpr [List with 1 elements]
         ||  |         []> [21, 10] StringLiteral (FAUX 1)
         ||  |             type: string
         ||  `> ListInst [List with 1 elements]
         ||     []> [22, 3] IfThenElse
         ||         +> [22, 11] Equals
         ||         |  type: boolean
         ||         |  +> [22, 11] Int (1)
         ||         |  |  type: int
         ||         |  `> [22, 14] Int (1)
         ||         |     type: int
         ||         +> ListInst [List with 1 elements]
         ||         |  []> [23, 2] Println
         ||         |      `> [23, 10] ListExpr [List with 1 elements]
         ||         |         []> [23, 10] StringLiteral (Hello 2)
         ||         |             type: string
         ||         `> ListInst [List with 0 elements]
         []> [26, 1] IfThenElse
             +> [26, 4] Or
             |  type: boolean
             |  +> [26, 4] And
             |  |  type: boolean
             |  |  +> [26, 4] Greater
             |  |  |  type: boolean
             |  |  |  +> [26, 4] Int (2)
             |  |  |  |  type: int
             |  |  |  `> [26, 6] Int (3)
             |  |  |     type: int
             |  |  `> [26, 11] BooleanLiteral (true)
             |  |     type: boolean
             |  `> [26, 19] BooleanLiteral (false)
             |     type: boolean
             +> ListInst [List with 1 elements]
             |  []> [27, 2] Println
             |      `> [27, 10] ListExpr [List with 1 elements]
             |         []> [27, 10] StringLiteral (FAUX 2)
             |             type: string
             `> ListInst [List with 1 elements]
                []> [30, 2] Println
                    `> [30, 10] ListExpr [List with 1 elements]
                       []> [30, 10] StringLiteral (Hello 3)
                           type: string
