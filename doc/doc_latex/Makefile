FILE = modelo-ufmg

LATEX_EXECUTABLE = pdflatex

${FILE}.pdf: *.tex ${FILE}.bib
	${LATEX_EXECUTABLE} ${FILE}.tex
	${LATEX_EXECUTABLE} ${FILE}.tex
	bibtex ${FILE}
	${LATEX_EXECUTABLE} ${FILE}.tex
	${LATEX_EXECUTABLE} ${FILE}.tex

clean:
	rm -f *.log *.aux *.toc *.lot *.lof *.bbl *.bbg *.blg *.lsg

clean-all:
	rm -f *.log *.aux *.toc *.lot *.lof *.bbl *.bbg *.blg *.lsg *.dvi *.pdf
