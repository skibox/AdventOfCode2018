def compare?(ch1, ch2)
  if ( ch1.upcase == ch2 || ch1.downcase == ch2 ) && ch1 != ch2
    true
  else
    false
  end
end

polymer = IO.readlines('../resources/polymer.txt')[0].split('')
# polymer = IO.readlines('resources/polymer.txt')[0].split('')
# polymer = "dabAcCaCBAcCcaDA".split('')
polymer_output = []

until polymer.empty?
  if polymer_output.empty?
    polymer_output << polymer.shift
  elsif compare?(polymer.first, polymer_output.last)
    polymer.shift
    polymer_output.pop
  else
    polymer_output << polymer.shift
  end
end

counter = 0
polymer_output.each_with_index do |_item, index|
  if compare?(polymer_output[index], polymer_output[index + 1])
    counter += 1
  end
end

puts "Bad links: #{counter}"

puts polymer_output.length
puts polymer_output.join('').to_s
puts polymer.join.to_s
