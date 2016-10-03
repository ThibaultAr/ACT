ALL = tp2

compile: $(ALL)

exec: compile
	./tp2 < $(FILE)

%: %.c
	gcc $< -o $@ -g

clean:
	rm $(ALL)
