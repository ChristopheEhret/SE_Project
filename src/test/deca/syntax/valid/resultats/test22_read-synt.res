`> [14, 0] Program
   +> ListDeclClass [List with 0 elements]
   `> [14, 0] Main
      +> [16, 7] ListDeclVar [List with 2 elements]
      |  []> [15, 5] DeclVar
      |  ||  +> [15, 1] Identifier (int)
      |  ||  +> [15, 5] Identifier (x)
      |  ||  `> NoInitialization
      |  []> [16, 7] DeclVar
      |      +> [16, 1] Identifier (float)
      |      +> [16, 7] Identifier (f)
      |      `> NoInitialization
      `> ListInst [List with 2 elements]
         []> [18, 1] Assign
         ||  +> [18, 1] Identifier (x)
         ||  `> [18, 3] ReadInt
         []> [19, 1] Assign
             +> [19, 1] Identifier (f)
             `> [19, 3] ReadFloat
