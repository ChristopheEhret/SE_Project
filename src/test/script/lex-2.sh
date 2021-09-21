#! /bin/sh

# Auteur : Adrien Kaufman
# Version initiale : 11/01/2021

# Script de test pour l'analyseur lexical 
# Teste tous les fichiers valides lexicalement
# mais invalides syntaxiquement
# Redirige la sortie standard et les erreurs dans un meme fichier

# On se place dans le repertoire du projet
cd "$(dirname "$0")"/../../.. || exit 1

PATH=./src/test/script/launchers:"$PATH"

# Tests valides syntaxiquement
echo "Analyseur lexical : tests invalides"
for i in src/test/deca/codegen/valid/*.deca
do
	VAR="${i%/*}"/resultats/"${i##*/}"
	echo "${i##*/}"
	./src/main/bin/decac "$i"
	ima "${i%deca}"ass > "${VAR%.deca}"-ima.res 2>&1
	rm "${i%deca}"ass
	#test_context "$i" > "${VAR%.deca}"-cont.res 2>&1
done
