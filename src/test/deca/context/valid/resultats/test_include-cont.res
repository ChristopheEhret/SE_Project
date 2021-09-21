`> [1, 0] Program
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
   `> [1, 0] Main
      +> [2, 5] ListDeclVar [List with 1 elements]
      |  []> [2, 5] DeclVar
      |      +> [2, 1] Identifier (int)
      |      |  definition: type defined at [0, 0], type=int
      |      +> [2, 5] Identifier (x)
      |      |  definition: variable defined at [2, 5], type=int
      |      `> [2, 7] Initialization
      |         `> [2, 9] Int (5)
      |            type: int
      `> ListInst [List with 1 elements]
         []> [1, 1] Println
             `> [1, 9] ListExpr [List with 2 elements]
                []> [1, 9] StringLiteral (Test Include : )
                ||  type: string
                []> [1, 2] Identifier (x)
                    definition: variable defined at [2, 5], type=int
