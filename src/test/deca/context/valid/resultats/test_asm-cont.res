`> [15, 0] Program
   +> [15, 0] ListDeclClass [List with 2 elements]
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
   |  []> [15, 0] DeclClass
   |      +> [15, 6] Identifier (A)
   |      |  definition: type defined at [15, 0], type=A
   |      +> Identifier (Object)
   |      |  definition: type defined at [0, 0], type=Object
   |      +> ListDeclField [List with 0 elements]
   |      `> ListDeclMethod [List with 1 elements]
   |         []> [16, 1] DeclMethod
   |             +> [16, 1] Identifier (void)
   |             |  definition: type defined at [0, 0], type=void
   |             +> [16, 6] Identifier (Asm)
   |             |  definition: method defined at [16, 1], type=void
   |             +> ListDeclParam [List with 0 elements]
   |             `> [16, 16] MethodAsm
   |                `> [16, 16] StringLiteral ( hello \n multiline )
   |                   type: string
   `> [18, 0] Main
      +> [19, 3] ListDeclVar [List with 1 elements]
      |  []> [19, 3] DeclVar
      |      +> [19, 1] Identifier (A)
      |      |  definition: type defined at [15, 0], type=A
      |      +> [19, 3] Identifier (a)
      |      |  definition: variable defined at [19, 3], type=A
      |      `> [19, 5] Initialization
      |         `> [19, 7] New
      |            type: A
      |            `> [19, 11] Identifier (A)
      |               definition: type defined at [15, 0], type=A
      `> ListInst [List with 1 elements]
         []> [20, 1] MethodCall
             type: void
             +> [20, 1] Identifier (a)
             |  definition: variable defined at [19, 3], type=A
             +> [20, 3] Identifier (Asm)
             |  definition: method defined at [16, 1], type=void
             `> ListExpr [List with 0 elements]
