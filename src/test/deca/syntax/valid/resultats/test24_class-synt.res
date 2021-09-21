`> [14, 0] Program
   +> [20, 0] ListDeclClass [List with 3 elements]
   |  []> [14, 0] DeclClass
   |  ||  +> [14, 6] Identifier (A)
   |  ||  +> Identifier (Object)
   |  ||  +> ListDeclField [List with 0 elements]
   |  ||  `> ListDeclMethod [List with 0 elements]
   |  []> [17, 0] DeclClass
   |  ||  +> [17, 6] Identifier (B)
   |  ||  +> Identifier (Object)
   |  ||  +> ListDeclField [List with 0 elements]
   |  ||  `> ListDeclMethod [List with 0 elements]
   |  []> [20, 0] DeclClass
   |      +> [20, 6] Identifier (C)
   |      +> [20, 8] Identifier (A)
   |      +> ListDeclField [List with 0 elements]
   |      `> ListDeclMethod [List with 0 elements]
   `> [23, 0] Main
      +> [25, 3] ListDeclVar [List with 2 elements]
      |  []> [24, 3] DeclVar
      |  ||  +> [24, 1] Identifier (A)
      |  ||  +> [24, 3] Identifier (a)
      |  ||  `> [24, 5] Initialization
      |  ||     `> [24, 7] New
      |  ||        `> [24, 11] Identifier (A)
      |  []> [25, 3] DeclVar
      |      +> [25, 1] Identifier (C)
      |      +> [25, 3] Identifier (c)
      |      `> [25, 5] Initialization
      |         `> [25, 7] New
      |            `> [25, 11] Identifier (C)
      `> ListInst [List with 1 elements]
         []> [26, 1] Println
             `> [26, 9] ListExpr [List with 1 elements]
                []> [26, 9] StringLiteral ()
