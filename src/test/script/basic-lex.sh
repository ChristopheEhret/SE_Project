#! /bin/sh

# Auteur : gl27
# Version initiale : 01/01/2021

# Base pour un script de test de la lexicographie.
# On teste un fichier valide et un fichier invalide.
# Il est conseillé de garder ce fichier tel quel, et de créer de
# nouveaux scripts (en s'inspirant si besoin de ceux fournis).

# Il faudrait améliorer ce script pour qu'il puisse lancer test_lex
# sur un grand nombre de fichiers à la suite.

# On se place dans le répertoire du projet (quel que soit le
# répertoire d'où est lancé le script) :
cd "$(dirname "$0")"/../../.. || exit 1

PATH=./src/test/script/launchers:"$PATH"

# tests valides lexicalements pour l'étape A.
# echo "test_hello_empty.deca :"
# test_lex src/test/deca/syntax/valid/test_hello_empty.deca

# echo "test_hello_minimal.deca :"
# test_lex src/test/deca/syntax/valid/test_hello_minimal.deca

# echo "test_hello_spaces1.deca :"
# test_lex src/test/deca/syntax/valid/test_hello_spaces1.deca


# /!\ test valide lexicalement, mais invalide pour l'étape A.
# test_lex peut au choix afficher les messages sur la sortie standard
# (1) ou sortie d'erreur (2). On redirige la sortie d'erreur sur la
# sortie standard pour accepter les deux (2>&1)
# echo "test_hello_basic_tokens1.deca :"
# test_lex src/test/deca/syntax/invalid/test_hello_basic_tokens1.deca

if test_lex src/test/deca/syntax/invalid/provided/simple_lex.deca 2>&1 \
    | head -n 1 | grep -q 'simple_lex.deca:[0-9]'
then
    echo "Echec inattendu de test_lex"
    exit 1
else
    echo "OK"
fi

# Ligne 10 codée en dur. Il faudrait stocker ça quelque part ...
if test_lex src/test/deca/syntax/invalid/provided/chaine_incomplete.deca 2>&1 \
    | grep -q -e 'chaine_incomplete.deca:10:'
then
    echo "Echec attendu pour test_lex"
else
    echo "Erreur non detectee par test_lex pour chaine_incomplete.deca"
    exit 1
fi

