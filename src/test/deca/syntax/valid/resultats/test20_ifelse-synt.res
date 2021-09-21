`> [17, 0] Program
   +> ListDeclClass [List with 0 elements]
   `> [17, 0] Main
      +> ListDeclVar [List with 0 elements]
      `> ListInst [List with 3 elements]
         []> [18, 1] IfThenElse
         ||  +> [18, 4] Equals
         ||  |  +> [18, 4] Int (2)
         ||  |  `> [18, 7] Int (2)
         ||  +> ListInst [List with 1 elements]
         ||  |  []> [19, 2] Println
         ||  |      `> [19, 10] ListExpr [List with 1 elements]
         ||  |         []> [19, 10] StringLiteral (Hello 2)
         ||  `> ListInst [List with 0 elements]
         []> [22, 1] IfThenElse
         ||  +> [22, 4] Greater
         ||  |  +> [22, 4] Int (2)
         ||  |  `> [22, 6] Int (3)
         ||  +> ListInst [List with 1 elements]
         ||  |  []> [23, 2] Println
         ||  |      `> [23, 10] ListExpr [List with 1 elements]
         ||  |         []> [23, 10] StringLiteral (FAUX 1)
         ||  `> ListInst [List with 1 elements]
         ||     []> [24, 3] IfThenElse
         ||         +> [24, 11] Equals
         ||         |  +> [24, 11] Int (1)
         ||         |  `> [24, 14] Int (1)
         ||         +> ListInst [List with 1 elements]
         ||         |  []> [25, 2] Println
         ||         |      `> [25, 10] ListExpr [List with 1 elements]
         ||         |         []> [25, 10] StringLiteral (Hello 3)
         ||         `> ListInst [List with 0 elements]
         []> [27, 1] IfThenElse
             +> [27, 4] Greater
             |  +> [27, 4] Int (2)
             |  `> [27, 6] Int (3)
             +> ListInst [List with 1 elements]
             |  []> [28, 2] Println
             |      `> [28, 10] ListExpr [List with 1 elements]
             |         []> [28, 10] StringLiteral (FAUX 2)
             `> ListInst [List with 1 elements]
                []> [30, 2] Println
                    `> [30, 10] ListExpr [List with 1 elements]
                       []> [30, 10] StringLiteral (Hello 4)
