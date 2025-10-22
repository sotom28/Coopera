public class CooperaService {

    private final CooperaRepository cooperaRepository;

    public CooperaService(CooperaRepository cooperaRepository) {
        this.cooperaRepository = cooperaRepository;
    }

    public List<Item> findAll() {
        return cooperaRepository.findAll();
    }

    public Optional<Item> findById(Long id) {
        return cooperaRepository.findById(id);
    }
}