ALL = tp1

compile: $(ALL)

execute: compile
	./tp1 $2

%: %.c
	gcc $< -o $@

clean:
	rm $(ALL)
