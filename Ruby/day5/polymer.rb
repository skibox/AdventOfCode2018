def improve_polymer
  polymer = fetch_polymer

  ('a'..'z').each do |unit_type|
    polymer_copy = polymer.clone

    polymer_copy.delete unit_type
    polymer_copy.delete unit_type.upcase

    puts  unit_type.upcase +
          unit_type +
          ': polymer length = ' +
          reacted_polymer(polymer_copy).length.to_s

  end
end

def reacted_polymer(polymer_to_react)
  polymer_output = []

  until polymer_to_react.empty?
    if polymer_output.empty?
      polymer_output << polymer_to_react.shift
    elsif compare?(polymer_to_react.first, polymer_output.last)
      polymer_to_react.shift
      polymer_output.pop
    else
      polymer_output << polymer_to_react.shift
    end
  end

  polymer_output
end

def compare?(ch1, ch2)
  if (ch1.upcase == ch2 || ch1.downcase == ch2) && ch1 != ch2
    true
  else
    false
  end
end

def fetch_polymer
  IO.readlines('../resources/polymer.txt')[0].split('')
end

puts 'Part 1: ' + reacted_polymer(fetch_polymer).length.to_s
puts
puts

improve_polymer
