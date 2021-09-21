#! /bin/sh

# Auteur : Adrien Kaufman
# Version initiale : 11/01/2021

# Script de test pour l'analyseur syntaxique
# Teste tous les fichiers valides syntaxiquement
# Redirige la sortie standard et les erreurs dans un meme fichier

# On se place dans le repertoire du projet
cd "$(dirname "$0")"/../../.. || exit 1

PATH=./src/test/script/launchers:"$PATH"

# Tests valides syntaxiquement
echo "Analyseur syntaxique : tests valides"
for i in ./src/test/deca/syntax/valid/*.deca
do
	echo "$i"
	test_synt "$i" > "${i%.deca}"-synt.res 2>&1
done
