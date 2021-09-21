`> [15, 0] Program
   +> ListDeclClass [List with 0 elements]
   `> [15, 0] Main
      +> [18, 5] ListDeclVar [List with 2 elements]
      |  []> [17, 8] DeclVar
      |  ||  +> [17, 1] Identifier (string)
      |  ||  +> [17, 8] Identifier (s)
      |  ||  `> [17, 10] Initialization
      |  ||     `> [17, 12] StringLiteral (Hello)
      |  []> [18, 5] DeclVar
      |      +> [18, 1] Identifier (int)
      |      +> [18, 5] Identifier (i)
      |      `> [18, 7] Initialization
      |         `> [18, 9] Int (12)
      `> ListInst [List with 2 elements]
         []> [20, 1] Println
         ||  `> [20, 9] ListExpr [List with 1 elements]
         ||     []> [20, 9] Identifier (s)
         []> [21, 1] Println
             `> [21, 9] ListExpr [List with 1 elements]
                []> [21, 9] Identifier (i)
