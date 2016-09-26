ALL = tp1

compile: $(ALL)

exec: compile
	./tp1 < $(FILE)

%: %.c
	gcc $< -o $@

clean:
	rm $(ALL)
