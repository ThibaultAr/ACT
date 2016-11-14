ALL = tp1

compile: $(ALL)

exec: compile
	./tp1 < $(FILE)

%: src/%.c
	gcc $< -o $@ -g

clean:
	rm $(ALL)
