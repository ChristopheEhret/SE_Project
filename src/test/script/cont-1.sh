#! /bin/sh

# Auteur : Adrien Kaufman
# Version initiale : 14/01/2021

# Script de test pour la decoration de l'arbre
# Tous les fichiers testes sont ceux de l'etape A
# Pas de test invalide pour cette etape
# Redirige la sortie standard et les erreurs dans un meme fichier

# On se place dans le repertoire du projet
cd "$(dirname "$0")"/../../.. || exit 1

PATH=./src/test/script/launchers:"$PATH"

# Tests valides contextuellement 
echo "Décoration de l'arbre : tests valides"
for i in ./src/test/deca/context/valid/*.deca
do
	echo "$i"
	test_context "$i" > "${i%.deca}"-cont.res 2>&1
done

# Tests invalides contextuellement 
echo "Décoration de l'arbre : tests invalides"
for i in ./src/test/deca/context/invalid/*.deca
do
	echo "$i"
	test_context "$i" > "${VAR%.deca}"-cont.res 2>&1
done
