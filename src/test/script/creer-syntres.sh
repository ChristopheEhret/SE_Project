#!/bin/sh

# cree le -lex.res d'un fichier dont on est sur que test-lex est bon

cd "$(dirname "$0")"/../../.. || exit 1

PATH=./src/test/script/launchers:"$PATH"

VAR="${@%/*}"/resultats/"${@##*/}"

test_synt "$@" > "${VAR%.deca}"-synt.res 2>&1