def final_field(field, fixed_points)
  field.each do |point, closest|
    field[point] = get_closest_point(point, fixed_points)
  end

  field
end

def get_closest_point(point, fixed_points)
  distances = []
  closest = '-6969c'
  fixed_points.each do |fixed_point, value|
    distance = calculate_distance(point, fixed_point)
    distances << [distance, value]
    
    if point == fixed_point
      closest = 's'
      break
    end
  end

  distances.sort! { |a, b| a[0] <=> b[0] }

  unless closest == 's'
    closest = if distances[0][0] != distances[1][0]
                distances[0][1].to_s + 'c'
              else
                '.'
              end
  end
  
  closest
end

def calculate_distance(point1, point2)
  p1 = point1[0].to_i
  p2 = point1[1].to_i
  q1 = point2[0].to_i
  q2 = point2[1].to_i

  (p1 - q1).abs + (p2 - q2).abs
end

def initial_field
  field = {}
  max_values = calc_arr_size.clone

  (0..max_values[1]).each do |col|
    (0..max_values[0]).each do |row|
      field[[col, row]] = -1
    end
  end

  field
end

def fetch_fixed_points
  hits = {}

  (1..parse_coordinates.length).each_with_index do |item, index|
    hits[parse_coordinates[index]] = item
  end

  hits
end

def calc_arr_size
  bottom_right = [-1, -1]

  parse_coordinates.each do |item|
    bottom_right[0] = item[0].to_i if item[0].to_i > bottom_right[0]
    bottom_right[1] = item[1].to_i if item[1].to_i > bottom_right[1]
  end

  bottom_right
end

def parse_coordinates
  coords = []
  fetch_coordinates.each_with_index do |_item, index|
    coords <<
      fetch_coordinates
      .at(index)
      .gsub(/\s|\n/, '')
      .split(',')
      .collect(&:to_i)
  end

  coords
end

def fetch_coordinates
  IO.readlines('../resources/coordinates')
end

def supl_hash(final_field, hash)
  final_field.each do |_key, value|
    hash[value] += 1
  end

  hash
end

field = initial_field.clone
fixed_points = fetch_fixed_points.clone

final_field = final_field(field,fixed_points).clone

comp_arr = ('1'..'50').to_a
comp_arr << '.'
comp_arr << 's'

comp_hash = {}

comp_arr.each do |item|
  item << 'c' unless item == '.' || item == 's'
  comp_hash[item] = 0
end
# puts calculate_distance([0,0],[23,5])
# puts comp_hash

# puts fetch_fixed_points
p supl_hash(final_field, comp_hash)
# p fixed_points
puts
puts

# p get_closest_point([100,121],fixed_points)


# puts final_field.first
