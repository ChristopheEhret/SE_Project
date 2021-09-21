`> [14, 0] Program
   +> ListDeclClass [List with 0 elements]
   `> [14, 0] Main
      +> [15, 5] ListDeclVar [List with 1 elements]
      |  []> [15, 5] DeclVar
      |      +> [15, 1] Identifier (int)
      |      +> [15, 5] Identifier (x)
      |      `> NoInitialization
      `> ListInst [List with 2 elements]
         []> [17, 1] While
         ||  +> [17, 7] Equals
         ||  |  +> [17, 7] Int (1)
         ||  |  `> [17, 10] BooleanLiteral (true)
         ||  `> ListInst [List with 1 elements]
         ||     []> [17, 16] NoOperation
         []> [18, 1] While
             +> [18, 7] Greater
             |  +> [18, 7] BooleanLiteral (false)
             |  `> [18, 15] Float (8.4)
             `> ListInst [List with 1 elements]
                []> [20, 2] While
                    +> [20, 8] Assign
                    |  +> [20, 8] Identifier (x)
                    |  `> [20, 10] BooleanLiteral (true)
                    `> ListInst [List with 1 elements]
                       []> [21, 3] Divide
                           +> [21, 3] Int (4)
                           `> [21, 5] BooleanLiteral (false)
